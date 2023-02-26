package telran.pulse.monitoring.security;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AccountingManagement {
	private ConcurrentHashMap<String, Account> accounts;
@Bean
	MapReactiveUserDetailsService getUserDetailsService() {
		accounts = new ConcurrentHashMap<>();
		accounts.put("admin@tel-ran.co.il", new Account("admin@tel-ran.co.il",
				"$2a$10$0d.gqun7BTHuD1lNHDNWAujVXkHwcpZIXGiXb8oJvA/JbJjfKcrpm", "ADMIN"));
		accounts.put("user@tel-ran.co.il", new Account("user@tel-ran.co.il",
				"$2a$10$rSdI0lSvHmwhzOxLQ1olOujYO4gIGgRhst03Si3vKxtpASI/4W3Ni", "USER"));
		ConcurrentHashMap<String, UserDetails> mapUsers = new ConcurrentHashMap<>();
		accounts.forEach((k,v) -> mapUsers.put(k, new User(k, v.getHashPassword(),
				AuthorityUtils.createAuthorityList("ROLE_" + v.getRole()))));
		return new MapReactiveUserDetailsService(mapUsers);
	}
	public Account getAccount(String username) {
		
		return accounts.get(username);
	}
}
