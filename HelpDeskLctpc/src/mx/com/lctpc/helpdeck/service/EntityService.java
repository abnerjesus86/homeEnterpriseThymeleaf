package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.EntityDao;
import mx.com.lctpc.helpdeck.pojo.AEntities;

@Service
public class EntityService {

	@Autowired
	private EntityDao entityDao;

	public List<AEntities> findAllEntity() {
		return entityDao.findAllEntity();
	}

	public void saveOrUpdateEntity( AEntities p_ent ) {
		if (p_ent.getEnttId() == null || findEntityById(p_ent.getEnttId()) == null) {

			entityDao.saveEntity(p_ent);
			;
		} else {

			entityDao.updateEntity(p_ent);
			;
		}
	}

	public AEntities findEntityById( BigDecimal p_entId ) {
		return entityDao.findEntityById(p_entId);
	}
	
	public void deleteEntity(AEntities p_ent){
		p_ent.setEnttActive(false);
		entityDao.deleteEntity(p_ent);
	}
	
}
