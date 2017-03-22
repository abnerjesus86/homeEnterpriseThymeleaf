package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.OwnerDao;
import mx.com.lctpc.helpdeck.pojo.Owner;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerDao ownerDao;
	
	public List<Owner> findAllOwners(){
		return ownerDao.findAllOwners();
	}
	public Owner findOwnerById(BigDecimal p_ownrId){
		return ownerDao.findOwnerById(p_ownrId);
	}
	public List<Owner> findOwnersActive(){
		return ownerDao.findOwnersActive();
	}
	public void save(Owner p_Ownr){
		ownerDao.save(p_Ownr);
	}
	public void update(Owner p_Ownr){
		ownerDao.update(p_Ownr);
	}
	public void delete(Owner p_Ownr){
		ownerDao.delete(p_Ownr);
	}
}
