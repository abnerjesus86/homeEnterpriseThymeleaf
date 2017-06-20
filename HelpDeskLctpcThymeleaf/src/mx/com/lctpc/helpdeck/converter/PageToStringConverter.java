package mx.com.lctpc.helpdeck.converter;

import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.pojo.Page;

public class PageToStringConverter implements Converter<Page, String > {
	
	@Override
	public String convert( Page p_arg0 ) {
		return p_arg0.getPageId().toPlainString();
	}
	
}
