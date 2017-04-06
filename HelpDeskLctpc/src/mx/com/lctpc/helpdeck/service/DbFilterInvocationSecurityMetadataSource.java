package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/*@Component*/
public class DbFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
	
	@Autowired
	private SecurityLctService secLctService;
	
	@Autowired
	private UrlCache urlCache;
	
	private HashMap<String, List<String>> g_urlRoles;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes( Object p_arg0 ) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Object principal = null;
		if(SecurityContextHolder.getContext().getAuthentication()==null || SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null)
        {
              System.out.println("An Authentication object was not found in the SecurityContext");  
			//throw new AuthenticationCredentialsNotFoundException("An Authentication object was not found in the SecurityContext");
        }
        else
        {
        	principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	System.out.println( "Usuario login "+ principal );
        	System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        }
		
		
		
		FilterInvocation fi=(FilterInvocation)p_arg0;
		String url=fi.getRequestUrl();
		System.out.println("Url solicitud : "+ url);
		
		if(url.indexOf('?')!=-1)
            url = url.substring(0, url.indexOf('?'));
        url = url.trim();
        System.out.println("nueva url "+ url);
        
		/*if(!url.endsWith("/")) {
			
			if((url.split("/").length-1) > 1){
				int lastSlashIndex = url.lastIndexOf("/");
				url = url.substring(0, lastSlashIndex + 1);
			}
			
			System.out.println("nueva url "+ url);
		}*/
		
		List<String> roles_ = g_urlRoles.get(url);
		
		
		if( roles_ == null ){
			return null;
		}
		System.out.println("Rol solicitud : "+ roles_.toString());
		
		String[] stockArr = new String[roles_.size()];
		stockArr = roles_.toArray(stockArr);
		
		System.out.println("stockArr " + stockArr.toString());
		return SecurityConfig.createList(stockArr);
	}

	@Override
	public boolean supports( Class<?> p_arg0 ) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		secLctService.getPageRoles(new BigDecimal(3));
		
		this.g_urlRoles = urlCache.getUrlRoles();
		
		System.out.println("Url Roles object : "+g_urlRoles);
	}

}
