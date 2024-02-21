/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.mail.utils;

import javax.servlet.http.HttpServletRequest;

import com.favourite.collections.infrastructure.core.domain.AppUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessagesTemplate {
	public String welcomeMessageTemplate(AppUser appUser, String token, HttpServletRequest request) {
		String message = "<html> " + "<body>" + "<h5>Hi " + appUser.getFirstname() + " " + appUser.getLastname()
				+ ",<h5> <br>" + "<p>Thank you for your interest in joining Top Restaurant."
				+ "To complete your registration, we need you to verify your email address \n"
				+ "<br><a href=[[TOKEN_URL]]>CLICK TO VERIFY YOUR ACCOUNT. </a><p>" + "</body> " + "</html>";

		String url = getApplicationUrl(request) + "verify-registration?token=" + token;
		log.info("VERIFY_URL: {}", url);
		message = message.replace("[[TOKEN_URL]]", url);
		return message;
	}

	public String resendTokenTemplate(AppUser appUser, String token, HttpServletRequest request) {
		String message = "<html> " + "<body>" + "<h5>Hi " + appUser.getFirstname() + " " + appUser.getLastname()
				+ ",<h5> <br>" + "<p>Thank you for your interest in joining Top Restaurant."
				+ "<br><a href=[[TOKEN_URL]]>CLICK TO VERIFY YOUR ACCOUNT. </a><p>" + "</body> " + "</html>";

		String url = getApplicationUrl(request) + "verify-registration?token=" + token;
		log.info("VERIFY_URL: {}", url);
		message = message.replace("[[TOKEN_URL]]", url);
		return message;
	}

	private String getApplicationUrl(HttpServletRequest request) {
		// return "http://" + request.getServerName() + ":3000";
		return "http://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/auth/";
	}
}
