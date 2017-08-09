package mx.com.lctpc.helpdeck.converter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import mx.com.lctpc.helpdeck.dao.EntityDao;
import mx.com.lctpc.helpdeck.dao.PageDao;
import mx.com.lctpc.helpdeck.pojo.AEntities;
import mx.com.lctpc.helpdeck.pojo.PageEntity;

public class EntityToPageEntityConverter implements Converter<String, PageEntity> {
	
	@Autowired
	private EntityDao entDao;
	@Autowired
	private PageDao pagDao;
	
	@Override
	public PageEntity convert( String p_arg0 ) {
		//BigDecimal l_id =  new BigDecimal(p_arg0);
		String l_ids[] = p_arg0.split("\\|");
		PageEntity l_pagEnt = new PageEntity();
		if(l_ids[0].equals("NEW")){
			BigDecimal l_idEnt =  new BigDecimal(l_ids[1]);
			AEntities l_ent = entDao.findEntityById(l_idEnt);
			l_pagEnt.setPaenEnttId(l_ent);
			
		}else{
			BigDecimal l_idPagEnt =  new BigDecimal(l_ids[0]);
			l_pagEnt = pagDao.findPageEntityById(l_idPagEnt);
		}
		/*AEntities l_ent = entDao.findEntityById(l_id);
		l_pagEnt.setPaenEnttId(l_ent);*/
		return l_pagEnt;
	}
	
}
