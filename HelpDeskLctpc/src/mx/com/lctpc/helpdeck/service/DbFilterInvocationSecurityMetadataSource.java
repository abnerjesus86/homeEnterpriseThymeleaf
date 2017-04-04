package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

@Component
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
		FilterInvocation fi=(FilterInvocation)p_arg0;
		String url=fi.getRequestUrl();
		
		List<String> roles_ = g_urlRoles.get(url);
		
		if( roles_ == null ){
			return null;
		}
		
		String[] stockArr = new String[roles_.size()];
		stockArr = roles_.toArray(stockArr);
		
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
