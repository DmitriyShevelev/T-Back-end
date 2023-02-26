package telran.courses.controller;

import java.util.Base64;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import telran.courses.api.*;
import telran.courses.api.dto.*;
import telran.courses.security.Account;
@RestController
@RequestMapping(value=ApiConstants.LOGIN_MAPPING)
public class AuthController {
	static Logger LOG = LoggerFactory.getLogger(AuthController.class);

	ConcurrentMap<String, Account> accounts;
	PasswordEncoder passwordEncoder;
	@Autowired
	public AuthController(ConcurrentMap<String, Account> accounts, PasswordEncoder passwordEncoder) {
		this.accounts = accounts;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping
	ResponseEntity<?> login(@RequestBody LoginData loginData) {
		Account account = accounts.get(loginData.email);
		LOG.debug("user {} performs login", loginData.email);
		if (account == null) {
			return wrongAccount();
		}
		if (!passwordEncoder.matches(loginData.password, account.getHashPassword())) {
			return wrongAccount();
		}
		
		return ResponseEntity.ok(getToken(loginData, account));
	}

	private AuthToken getToken(LoginData loginData, Account account) {
		String userPassword = String.format("%s:%s", loginData.email, loginData.password);
		String accessToken = "Basic " + Base64.getEncoder()
		.encodeToString(userPassword.getBytes());
		return new AuthToken(accessToken , account.getRole());
	}

	private ResponseEntity<?> wrongAccount() {
		
		return ResponseEntity.badRequest().body("");
	}
}
