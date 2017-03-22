package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.Owner;

public interface OwnerDao {
	public List<Owner> findAllOwners();
	public Owner findOwnerById(BigDecimal p_ownrId);
	public List<Owner> findOwnersActive();
	public void save(Owner p_Ownr);
	public void update(Owner p_Ownr);
	public void delete(Owner p_Ownr);
}
