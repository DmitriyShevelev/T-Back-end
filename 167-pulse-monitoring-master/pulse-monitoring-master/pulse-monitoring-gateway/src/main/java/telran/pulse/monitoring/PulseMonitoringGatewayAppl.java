package telran.pulse.monitoring;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.pulse.monitoring.dto.*;
import telran.pulse.monitoring.security.Account;
import telran.pulse.monitoring.security.AccountingManagement;

@SpringBootApplication
@RestController
public class PulseMonitoringGatewayAppl {
	@Autowired
PasswordEncoder passwordEncoder;
	@Autowired
	AccountingManagement accounting;
	public static void main(String[] args) {
		SpringApplication.run(PulseMonitoringGatewayAppl.class, args);

	}
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody LoginData loginData) {
		Account account = accounting.getAccount(loginData.email);
		if (account != null && passwordEncoder.matches(loginData.password,
				account.getHashPassword())) {
			return ResponseEntity.ok(getToken(loginData, account));
		}
		return ResponseEntity.badRequest().body("Wrong Credentials");
		
	}
	private LoginResponse getToken(LoginData loginData, Account account) {
		byte[] code = String.format("%s:%s", loginData.email, loginData.password).getBytes();
		return new LoginResponse("Basic " +
		Base64.getEncoder().encodeToString(code), account.getRole());
	}

}
