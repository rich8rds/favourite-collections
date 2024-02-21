/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.security.data.ChangePasswordData;
import com.favourite.collections.infrastructure.security.data.ForgotPasswordData;
import com.favourite.collections.infrastructure.security.data.LoginData;
import com.favourite.collections.infrastructure.security.data.RegistrationData;
import com.favourite.collections.infrastructure.security.data.UpdatePasswordData;
import com.favourite.collections.infrastructure.security.exception.ConstraintValidationException;
import com.favourite.collections.infrastructure.security.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/")
public class AuthController {
	private final AuthService authService;

	@PostMapping("login")
	public ResponseEntity<CommandResult> login(@RequestBody LoginData loginData) {
		return authService.loginUserIn(loginData);
	}

	@PostMapping("register")
	public ResponseEntity<CommandResult> login(@RequestBody @Valid RegistrationData registerData,
			HttpServletRequest request) {
		if (!registerData.passwordsMatch()) {
			throw new ConstraintValidationException("error.auth.passwords.do.not.match", "Passwords do not match");
		}
		return authService.register(registerData, request);
	}

	// todo: verify email GET

	// todo: reset-password-link send link to user to click GET
	// todo: change-password accept input to change password POST
	// todo: update-password: after user has logged in POST

	@GetMapping("verify-registration")
	public ResponseEntity<CommandResult> verifyAccount(@RequestParam String token) {
		return authService.verifyUserVerificationToken(token);
	}

	@GetMapping("resend-verification-token")
	public ResponseEntity<CommandResult> resendVerificationToken(@RequestParam String token,
			HttpServletRequest request) {
		return authService.resendVerificationToken(token, request);
	}

	@PostMapping("update-password")
	public ResponseEntity<CommandResult> updatePassword(@RequestBody UpdatePasswordData updatePasswordData) {
		if (!updatePasswordData.passwordsMatch()) {
			throw new ConstraintValidationException("error.auth.passwords.do.not.match", "Passwords do not match");
		}
		return authService.updatePassword(updatePasswordData);
	}

	@PostMapping("forgot-password")
	public ResponseEntity<CommandResult> forgotPassword(@RequestBody ForgotPasswordData forgotPasswordData,
			HttpServletRequest request) {
		return authService.getForgotPasswordToken(forgotPasswordData, request);
	}

	@PostMapping("change-password")
	public ResponseEntity<CommandResult> resetPassword(@RequestParam String token,
			@Valid @RequestBody ChangePasswordData changePasswordData) {
		if (!changePasswordData.passwordsMatch()) {
			throw new ConstraintValidationException("error.auth.passwords.do.not.match", "Passwords do not match");
		}
		return authService.changePasswordWithToken(token, changePasswordData);
	}

	// @PostMapping("customer/change-profile")
	// public ResponseEntity<CommandResult> updateProfile(@RequestBody
	// ProfileRequest profileRequest)
	// {
	// return authService.updateUserProfile(profileRequest);
	// }
	//
	// @GetMapping("customer/view-profile")
	// public ResponseEntity<CommandResult> getProfileDetails() {
	// return authService.getUserProfile();
	//
	// }

	// @PostMapping("auth/social-login")
	// public ResponseEntity<CommandResult> socialLogin(@Valid @RequestBody
	// SocialLoginRequest
	// socialLoginRequest) {
	// return authService.socialLogin(socialLoginRequest);
	// }
}
