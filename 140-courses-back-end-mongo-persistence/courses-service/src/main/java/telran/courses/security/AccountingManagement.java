package telran.courses.security;

import static telran.courses.api.ApiConstants.ADMIN;
import static telran.courses.api.ApiConstants.USER;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AccountingManagement {
	@Bean
	ConcurrentMap<String, Account> getAccounts() {
		ConcurrentHashMap<String, Account> res = new ConcurrentHashMap<>();
		res.put("admin@tel-ran.co.il",
				new Account("admin@tel-ran.co.il",
						"$2a$10$0d.gqun7BTHuD1lNHDNWAujVXkHwcpZIXGiXb8oJvA/JbJjfKcrpm", "ROLE_" + USER));
		res.put("user@tel-ran.co.il",
				new Account("user@tel-ran.co.il",
						"$2a$10$rSdI0lSvHmwhzOxLQ1olOujYO4gIGgRhst03Si3vKxtpASI/4W3Ni", "ROLE_" + ADMIN));
		return res;
	}
}
