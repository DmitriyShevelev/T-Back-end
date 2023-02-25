package telran.courses.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthJwtFilter extends OncePerRequestFilter {
	static Logger LOG = LoggerFactory.getLogger(AuthJwtFilter.class);
	private static final String BEARER = "Bearer ";
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = parseJwt(request);
		
		
		
		if (jwt != null) {
			String username = jwtUtils.validate(jwt);
			if(username == null) {
				response.setStatus(401);
				return;
			}
			LOG.debug("request contains jwt: {}", jwt);
			//fill security context
			UserDetails user = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(user, null,
							user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource()
					.buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} 
		filterChain.doFilter(request, response);
		
		

	}

	private String parseJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		String res = null;
		if (authHeader != null && authHeader.startsWith(BEARER)) {
			res = authHeader.substring(BEARER.length());
		}
		return res;
	}

}
