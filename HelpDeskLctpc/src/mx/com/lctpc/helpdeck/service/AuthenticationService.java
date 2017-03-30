package mx.com.lctpc.helpdeck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationService implements UserDetailsService {

	@Autowired
	private SecurityLctService secService;
	
	@Override
	public UserDetails loadUserByUsername( String p_arg0 ) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
