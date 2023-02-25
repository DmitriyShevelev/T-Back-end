package telran.courses.security;

import java.util.concurrent.ConcurrentMap;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	static Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);
@Autowired
private ConcurrentMap<String, Account> accounts;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accounts.get(username);
		if (account == null) {
			LOG.error("user {} not found", username);
			throw new UsernameNotFoundException("");
		}
		LOG.debug("username : {}", username);
		return new User(username, account.getHashPassword(),
				AuthorityUtils.createAuthorityList(account.getRole()));
	}

}
