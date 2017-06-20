package mx.com.lctpc.helpdeck.converter;

import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.pojo.PageEntity;


public class PageEntityToEntityConverter implements Converter<PageEntity, String> {

	@Override
	public String convert( PageEntity p_arg0 ) {
		//System.out.println("Entro a convertir PageEntity a Entity... "+ p_arg0.getPaenEnttId().getEnttId());
		//return p_arg0.getPaenEnttId().getEnttId().toPlainString();
		System.out.println("Entro a convertir PageEntity a Entity... "+ p_arg0.getPaenEnttId().getEnttId().toPlainString());
		return p_arg0.getPaenEnttId().getEnttId().toPlainString();
	}
	
}
