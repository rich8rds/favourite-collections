/* Collections #2024 */
package com.favourite.collections.infrastructure.core.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.favourite.collections.infrastructure.core.data.ApiError;
import com.favourite.collections.infrastructure.core.data.ApiSubError;
import com.favourite.collections.infrastructure.core.data.ApiValidationError;
import com.favourite.collections.infrastructure.security.exception.ConstraintValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<ApiSubError> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(new ApiValidationError(fieldName, errorMessage));
		});

		ApiError errorResponse = ApiError.builder().message("Error found!").debugMessage("error.validation.on.field")
				.subErrors(errors).build();
		return ResponseEntity.status(400).body(errorResponse);
	}

	@ExceptionHandler(AbstractPlatformException.class)
	public ResponseEntity<ApiError> abstractPlatformException(AbstractPlatformException ex) {

		ApiError errorResponse = ApiError.builder().message(ex.getDefaultUserMessage())
				.globalMessageCode(ex.getGlobalisationMessageCode()).debugMessage(ex.getLocalizedMessage())
				.subErrors(ex.getSubErrors()).build();

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ConstraintValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> constraintValidationException(ConstraintValidationException ex) {

		ApiError errorResponse = ApiError.builder().message(ex.getDefaultUserMessage())
				.globalMessageCode(ex.getGlobalisationMessageCode()).debugMessage(ex.getLocalizedMessage())
				.subErrors(ex.getSubErrors()).build();

		return ResponseEntity.badRequest().body(errorResponse);
	}
}
