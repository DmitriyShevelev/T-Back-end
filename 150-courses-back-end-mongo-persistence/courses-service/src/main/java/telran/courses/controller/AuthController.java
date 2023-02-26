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
import telran.courses.security.JwtUtils;
@RestController
@RequestMapping(value=ApiConstants.LOGIN_MAPPING)
@CrossOrigin
public class AuthController {
	static Logger LOG = LoggerFactory.getLogger(AuthController.class);

	ConcurrentMap<String, Account> accounts;
	PasswordEncoder passwordEncoder;
	JwtUtils jwtUtils;
	@Autowired
	public AuthController(ConcurrentMap<String, Account> accounts,
			PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
		this.accounts = accounts;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
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
		
		String accessToken = "Bearer " + jwtUtils.create(loginData.email);
		return new AuthToken(accessToken , account.getRole().replace("ROLE_", ""));
	}

	private ResponseEntity<?> wrongAccount() {
		LOG.error("wrong name or password");
		return ResponseEntity.badRequest().body("");
	}
}
