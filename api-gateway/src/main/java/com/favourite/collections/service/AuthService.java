/* Collections #2024 */
package com.favourite.collections.service;


import com.favourite.collections.commons.core.data.CommandResult;
import com.favourite.collections.commons.useradmin.data.ChangePasswordData;
import com.favourite.collections.commons.useradmin.data.ForgotPasswordData;
import com.favourite.collections.commons.useradmin.data.LoginData;
import com.favourite.collections.commons.useradmin.data.RegistrationData;
import com.favourite.collections.commons.useradmin.data.UpdatePasswordData;
import org.springframework.http.ResponseEntity;


import org.springframework.http.server.ServletServerHttpRequest;

public interface AuthService {

	ResponseEntity<CommandResult> loginUserIn(LoginData loginData);

	ResponseEntity<CommandResult> register(RegistrationData registerData);

	ResponseEntity<CommandResult> verifyUserVerificationToken(String token);

	ResponseEntity<CommandResult> resendVerificationToken(String token, ServletServerHttpRequest request);

	ResponseEntity<CommandResult> updatePassword(UpdatePasswordData updatePasswordData);

	ResponseEntity<CommandResult> getForgotPasswordToken(ForgotPasswordData forgotPasswordData,
                                                         ServletServerHttpRequest request);

	ResponseEntity<CommandResult> changePasswordWithToken(String token, ChangePasswordData changePasswordData);
}
