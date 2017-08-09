package mx.com.lctpc.helpdeck.converter;

import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.pojo.Application;

public class ApplicationToStringConverter implements Converter<Application, String > {
	
	@Override
	public String convert( Application p_arg0 ) {
		return p_arg0.getAppnId().toPlainString();
	}
	
}
