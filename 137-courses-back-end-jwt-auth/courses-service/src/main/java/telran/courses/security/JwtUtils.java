package telran.courses.security;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	static Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${app.jwt.exp.period: 3600000}")
long expTimePeriodMs;
	@Value("${app.jwt.secret}")
	String secret;
	public String create(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(getExpDate())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
				
	}
	private Date getExpDate() {
	
		return new Date(new Date().getTime() + expTimePeriodMs);
	}
	public String validate(String jwt) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
		} catch (ExpiredJwtException e) {
			LOG.error("token expired");
		} catch (UnsupportedJwtException | MalformedJwtException e) {
			LOG.error(e.toString());
		} catch (SignatureException e) {

			LOG.error("courrupted token");
		} catch (IllegalArgumentException e) {
			LOG.error("empty token");
		}
		return null;
	}
	
}
