/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.CommandResultBuilder;
import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.core.repository.AppUserRepository;
import com.favourite.collections.infrastructure.mail.data.EmailRequestData;
import com.favourite.collections.infrastructure.mail.exceptions.UserUnAuthorizedException;
import com.favourite.collections.infrastructure.mail.service.EmailService;
import com.favourite.collections.infrastructure.mail.utils.MessagesTemplate;
import com.favourite.collections.infrastructure.role.domain.Role;
import com.favourite.collections.infrastructure.role.repository.RoleRepository;
import com.favourite.collections.infrastructure.security.data.ChangePasswordData;
import com.favourite.collections.infrastructure.security.data.ForgotPasswordData;
import com.favourite.collections.infrastructure.security.data.LoginData;
import com.favourite.collections.infrastructure.security.data.RegistrationData;
import com.favourite.collections.infrastructure.security.data.UpdatePasswordData;
import com.favourite.collections.infrastructure.security.domain.Token;
import com.favourite.collections.infrastructure.security.exception.ConstraintValidationException;
import com.favourite.collections.infrastructure.security.repository.TokenRepository;
import com.favourite.collections.infrastructure.security.service.AuthService;
import com.favourite.collections.infrastructure.security.tokens.JwtTokenService;
import com.favourite.collections.infrastructure.security.util.AppContextUser;
import com.favourite.collections.infrastructure.security.util.TokenGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenService;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final AppContextUser appContextUser;
	private final MessagesTemplate messagesTemplate = new MessagesTemplate();
	private final TokenGenerator tokenGenerator;
	private final EmailService emailService;

	@Override
	public ResponseEntity<CommandResult> loginUserIn(LoginData loginData) {

		try {

			UserDetails user = userDetailsService.loadUserByUsername(loginData.getEmail());
			if (!user.isEnabled())
				throw new UsernameNotFoundException(
						"error.user.not.verified.or.active: Check your email to be verified!");
			if (!user.isAccountNonLocked()) {
				throw new AbstractPlatformException("error.message.invalid.account",
						"Please contact the administrator");
			}

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword()));
			if (authentication == null) {
				throw new AbstractPlatformException("error.msg.invalid.email.or.password",
						"Email or Password Invalid!");
			} else {
				String token = this.jwtTokenService.generateToken(authentication);
				return ResponseEntity.ok(new CommandResultBuilder().response("Login Successful").resourceId("200L")
						.token(token).build());
			}

		} catch (BadCredentialsException e) {
			throw new UserUnAuthorizedException("error.msg.auth.login", e.getMessage(), 401);
		} catch (UsernameNotFoundException e) {
			throw new AbstractPlatformException("error.msg.login", e.getMessage(), 404);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<CommandResult> register(RegistrationData registerData, HttpServletRequest request) {

		String email = registerData.getEmail();
		String firstname = registerData.getFirstname();
		String lastname = registerData.getLastname();
		String phoneNumber = registerData.getPhoneNumber();
		String password = registerData.getPassword();
		String roleName = registerData.getRoleName();

		boolean existsByEmail = this.appUserRepository.existsByEmail(email);
		if (BooleanUtils.isTrue(existsByEmail)) {
			throw new AbstractPlatformException("error.msg.user.email.already.exists",
					"User with email " + email + " already exists", 409);
		}

		Role role = roleRepository.findByNameAndAndIsDisabled(roleName, false)
				.orElse(roleRepository.findByName("CUSTOMER").orElseThrow(
						() -> new AbstractPlatformException("error.infrastructure.role.not.found", "Role not found!")));

		// todo: Create and add cart
		AppUser newAppuser = AppUser.builder().email(email).firstname(firstname).lastname(lastname).phoneNo(phoneNumber)
				.password(passwordEncoder.encode(password)).role(role).build();

		newAppuser = appUserRepository.save(newAppuser);

		Token token = tokenGenerator.generateToken(300L, ChronoUnit.SECONDS, newAppuser.getId());
		tokenRepository.save(token);

		// todo: sendEmail To user
		String messageBody = messagesTemplate.welcomeMessageTemplate(newAppuser, token.getToken(), request);

		EmailRequestData emailRequestData = EmailRequestData.builder().to(newAppuser.getEmail())
				.from("noreply@favourite-collections.ng").subject("WELCOME TO FAVOURITE COLLECTIONS").body(messageBody)
				.build();

		emailService.sendEmail(emailRequestData);
		return ResponseEntity.ok(new CommandResultBuilder().entityId(newAppuser.getId())
				.response("Registration Successful").message("Check your email to get verified").build());
	}

	@Override
	public ResponseEntity<CommandResult> verifyUserVerificationToken(String token) {
		Token verificationToken = getToken(token);
		Long id = verificationToken.getAppUserId();
		AppUser appUser = appUserRepository.findById(id == null ? 0L : id)
				.orElseThrow(() -> new UsernameNotFoundException("User with email does not exist"));

		appUser.setIsVerified(true);
		appUser.setIsActive(true);
		appUserRepository.save(appUser);

		tokenRepository.delete(verificationToken);

		return ResponseEntity
				.ok(new CommandResultBuilder().entityId(appUser.getId()).response("Account verification successful")
						.message("Login to your account to start shopping!").build());
	}

	private Token getToken(String token) {
		Token verificationToken = tokenRepository.findByToken(token)
				.orElseThrow(() -> new AbstractPlatformException("error.msg.auth.token.not.found", "Token Not Found"));

		long expirationTime = verificationToken.getExpirationTime();
		long now = Instant.now().getEpochSecond();

		if (now > expirationTime) {
			throw new ConstraintValidationException("error.msg.auth.token.expired",
					"Token has expired. Try resending verification token to email");
		}
		return verificationToken;
	}

	@Override
	public ResponseEntity<CommandResult> resendVerificationToken(String token, HttpServletRequest request) {
		Token verificationToken = tokenRepository.findByToken(token)
				.orElseThrow(() -> new AbstractPlatformException("error.auth.msg.token.not.found", "Token Not Found"));

		Long id = verificationToken.getAppUserId();

		if (verificationToken.getExpirationTime() > Instant.now().getEpochSecond()) {
			throw new BadCredentialsException("Token has expired. Resend token again");
		}

		AppUser appUser = appUserRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User with email does not exist"));

		tokenRepository.delete(verificationToken);
		// todo: send email
		String messageBody = messagesTemplate.resendTokenTemplate(appUser, verificationToken.getToken(), request);

		EmailRequestData emailRequestData = EmailRequestData.builder().to(appUser.getEmail())
				.from("noreply@favourite-collections.ng").subject("WELCOME TO FAVOURITE COLLECTIONS").body(messageBody)
				.build();

		emailService.sendEmail(emailRequestData);
		return ResponseEntity.ok(new CommandResultBuilder().entityId(appUser.getId())
				.response("Token sent successfully").message("Check your email to get verified").build());
	}

	@Override
	public ResponseEntity<CommandResult> updatePassword(UpdatePasswordData updatePasswordData) {
		String email = this.appContextUser.extractEmailFromPrincipal()
				.orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));

		String oldPassword = updatePasswordData.getOldPassword();
		String newPassword = updatePasswordData.getNewPassword();
		String confirmNewPassword = updatePasswordData.getConfirmNewPassword();

		AppUser appUser = appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));

		String dbPassword = appUser.getPassword();

		if (!passwordEncoder.matches(oldPassword, dbPassword)) {
			throw new ConstraintValidationException("error.msg.auth.passwords.do.not.match", "Passwords do not match!");
		}

		if (!confirmNewPassword.equals(newPassword)) {
			updatePasswordData.setConfirmNewPassword(newPassword);
		}

		appUser.setPassword(passwordEncoder.encode(newPassword));
		appUserRepository.save(appUser);

		return ResponseEntity.ok(
				new CommandResultBuilder().entityId(appUser.getId()).response("Password Successfully Changed").build());
	}

	@Override
	public ResponseEntity<CommandResult> getForgotPasswordToken(ForgotPasswordData forgotPasswordData,
			HttpServletRequest request) {
		AppUser appUser = appUserRepository.findByEmail(forgotPasswordData.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));

		// completeEvent(appUser, FORGOTPASSWORD, request);
		// todo: sent to email
		return ResponseEntity.ok(new CommandResultBuilder().entityId(appUser.getId())
				.response("Check your email to change password.").build());
	}

	@Override
	public ResponseEntity<CommandResult> changePasswordWithToken(String token, ChangePasswordData changePasswordData) {
		Token verificationToken = getToken(token);

		AppUser appUser = appUserRepository.findById(verificationToken.getAppUserId())
				.orElseThrow(() -> new UsernameNotFoundException("User with email does not exist"));

		String newPassword = changePasswordData.getNewPassword();
		appUser.setPassword(passwordEncoder.encode(newPassword));
		appUserRepository.save(appUser);
		tokenRepository.delete(verificationToken);

		return ResponseEntity.ok(new CommandResultBuilder().entityId(appUser.getId())
				.response("Password Successfully Changed!").build());
	}
}
