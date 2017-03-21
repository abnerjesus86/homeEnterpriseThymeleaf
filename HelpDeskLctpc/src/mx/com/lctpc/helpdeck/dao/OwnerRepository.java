package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.Owner;

@Transactional
@Repository
public interface OwnerRepository extends CrudRepository<Owner, BigDecimal>{
	
	public List<Owner> findOwners();
	 @Query("select ow from Owner ow where ow.g_OwnrActive = true")
	public List<Owner> findOwnerActive();
}
