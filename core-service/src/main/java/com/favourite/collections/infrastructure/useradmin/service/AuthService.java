/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.useradmin.data.ChangePasswordData;
import com.favourite.collections.infrastructure.useradmin.data.ForgotPasswordData;
import com.favourite.collections.infrastructure.useradmin.data.LoginData;
import com.favourite.collections.infrastructure.useradmin.data.RegistrationData;
import com.favourite.collections.infrastructure.useradmin.data.UpdatePasswordData;

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
