package telran.courses.security;

import java.util.concurrent.ConcurrentHashMap;
import static telran.courses.api.ApiConstants.*;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import telran.exceptions.SecurityExceptionsHandler;
@Configuration
@EnableWebSecurity
public class CoursesSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthJwtFilter authJwtFilter;
	@Autowired
	SecurityExceptionsHandler securityExceptions;

@Bean
PasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests()
		
		.antMatchers(LOGIN_MAPPING,WEBSOCKET_ENDPOINT, "/topic/**").permitAll()
		.antMatchers(HttpMethod.GET).hasAnyRole(ADMIN, USER)
		.anyRequest().hasRole(ADMIN);
		http.addFilterBefore(authJwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().accessDeniedHandler(securityExceptions)
		.authenticationEntryPoint(securityExceptions);
	}
}
