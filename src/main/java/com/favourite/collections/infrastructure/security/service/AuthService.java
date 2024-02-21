/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.security.data.ChangePasswordData;
import com.favourite.collections.infrastructure.security.data.ForgotPasswordData;
import com.favourite.collections.infrastructure.security.data.LoginData;
import com.favourite.collections.infrastructure.security.data.RegistrationData;
import com.favourite.collections.infrastructure.security.data.UpdatePasswordData;

public interface AuthService {

	ResponseEntity<CommandResult> loginUserIn(LoginData loginData);

	ResponseEntity<CommandResult> register(RegistrationData registerData, HttpServletRequest request);

	ResponseEntity<CommandResult> verifyUserVerificationToken(String token);

	ResponseEntity<CommandResult> resendVerificationToken(String token, HttpServletRequest request);

	ResponseEntity<CommandResult> updatePassword(UpdatePasswordData updatePasswordData);

	ResponseEntity<CommandResult> getForgotPasswordToken(ForgotPasswordData forgotPasswordData,
			HttpServletRequest request);

	ResponseEntity<CommandResult> changePasswordWithToken(String token, ChangePasswordData changePasswordData);
}
