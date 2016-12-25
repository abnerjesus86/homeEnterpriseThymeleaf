package mx.com.lctpc.helpdeck.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.dao.ApplicationDao;
import mx.com.lctpc.helpdeck.pojo.Application;

public class StringToApplicationConverter implements Converter<String, Application> {

	@Autowired
	private ApplicationDao appDao;
	
	@Override
	public Application convert( String p_arg0 ) {
		if(p_arg0.equals("NONE")){
			return null;
		}
		BigDecimal l_Id =  new BigDecimal(p_arg0);
		Application l_app = appDao.findApplicationById(l_Id);
		System.out.println("Entro convertir String a Application para guardar y establecer los nuevos valores ... "+ l_app.getAppnId());
		return l_app;
	}
	
}
