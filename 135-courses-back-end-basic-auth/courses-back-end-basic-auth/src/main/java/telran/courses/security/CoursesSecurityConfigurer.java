package telran.courses.security;

import java.util.concurrent.ConcurrentHashMap;
import static telran.courses.api.ApiConstants.*;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class CoursesSecurityConfigurer extends WebSecurityConfigurerAdapter {
@Bean
ConcurrentMap<String, Account> getAccounts() {
	ConcurrentHashMap<String, Account> res = new ConcurrentHashMap<>();
	res.put("admin@tel-ran.co.il",
			new Account("admin@tel-ran.co.il",
					"$2a$10$0d.gqun7BTHuD1lNHDNWAujVXkHwcpZIXGiXb8oJvA/JbJjfKcrpm", "ROLE_" + ADMIN));
	res.put("user@tel-ran.co.il",
			new Account("user@tel-ran.co.il",
					"$2a$10$rSdI0lSvHmwhzOxLQ1olOujYO4gIGgRhst03Si3vKxtpASI/4W3Ni", "ROLE_" + USER));
	return res;
}
@Bean
PasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.httpBasic();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests()
		
		.antMatchers(LOGIN_MAPPING,WEBSOCKET_ENDPOINT, "/topic/**").permitAll()
		.antMatchers(HttpMethod.GET).hasAnyRole(ADMIN, USER)
		.anyRequest().hasRole(ADMIN);
	}
}
