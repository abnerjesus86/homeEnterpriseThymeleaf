package mx.com.lctpc.helpdeck.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.dao.ApplicationDao;
import mx.com.lctpc.helpdeck.pojo.Application;

public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {
	
	@Override
	public BigDecimal convert( String p_arg0 ) {
		new BigDecimal(p_arg0);
		return null;
	}
	
}
