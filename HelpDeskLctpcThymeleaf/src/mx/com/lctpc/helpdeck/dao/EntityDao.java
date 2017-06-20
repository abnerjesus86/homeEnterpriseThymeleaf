package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.AEntities;

public interface EntityDao {
	public List<AEntities> findAllEntity();
	public void saveEntity(AEntities p_ent);
	public void updateEntity(AEntities p_ent);
	public AEntities findEntityById(BigDecimal p_entId);
	public void deleteEntity(AEntities p_ent);
}
