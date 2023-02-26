package telran.pulse.monitoring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
public class PulseMonitoringSecurityConfigurer  {
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
	 http.httpBasic();
	 http.cors().and().csrf().disable();
	 http.authorizeExchange().pathMatchers("/login").permitAll();
	 http.authorizeExchange().pathMatchers(HttpMethod.GET).hasAnyRole("USER", "ADMIN");
	 http.authorizeExchange().anyExchange().hasRole("ADMIN");
	 return http.build();
			
			
}
}
