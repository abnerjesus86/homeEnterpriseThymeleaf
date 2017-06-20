package mx.com.lctpc.helpdeck.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.dao.PageDao;
import mx.com.lctpc.helpdeck.pojo.Page;

public class StringToPageConverter implements Converter<String, Page> {

	@Autowired
	private PageDao pageDao;
	
	@Override
	public Page convert( String p_arg0 ) {
		if(p_arg0.equals("NONE")){
			return null;
		}
		BigDecimal l_Id =  new BigDecimal(p_arg0);
		Page l_pag = pageDao.findPageById(l_Id);
		return l_pag;
	}
	
}
