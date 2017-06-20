package mx.com.lctpc.helpdeck.converter;

import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.pojo.Application;

public class ApplicationToStringConverter implements Converter<Application, String > {
	
	@Override
	public String convert( Application p_arg0 ) {
		//BigDecimal l_Id =  new BigDecimal(p_arg0);
		//Application l_app = appDao.findApplicationById(l_Id);
		System.out.println("Entro convertir Application a String para establecer el valor en el select ... " + p_arg0.getAppnId());
		return p_arg0.getAppnId().toPlainString();
	}
	
}
