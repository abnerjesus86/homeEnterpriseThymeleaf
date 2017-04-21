package mx.com.lctpc.helpdeck.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.pojo.UserRole;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private SecurityLctService secService;
	
	@Override
	public UserDetails loadUserByUsername( String p_username ) throws UsernameNotFoundException {

		mx.com.lctpc.helpdeck.pojo.User l_user =  secService.getUser(p_username);
		
		if(l_user == null){
			throw new UsernameNotFoundException("User doesn`t exist");
		}
		
		List<UserRole> l_rolesAssignad = secService.getRoleAssigned(l_user.getUserId());
		Collection<GrantedAuthority> l_dbRoles = new ArrayList<GrantedAuthority>();
		for( UserRole l_roles : l_rolesAssignad ){			
			l_dbRoles.add( new SimpleGrantedAuthority( l_roles.getUsroRolId().getRoleName() ) );
		}
		User l_userAuth = new User(l_user.getUserUsername(),l_user.getPasswords().get(0).getPswdPassword(), l_dbRoles);
		return l_userAuth;
		
	}

}
