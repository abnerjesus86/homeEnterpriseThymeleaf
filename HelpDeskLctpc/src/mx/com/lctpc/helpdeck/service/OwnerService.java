package mx.com.lctpc.helpdeck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.OwnerRepository;
import mx.com.lctpc.helpdeck.pojo.Owner;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepository ownerDao;
	
	
	
	public List<Owner> findOwners(){
		return ownerDao.findOwners();
	}
	
	public List<Owner> findOwnerActive(){
		return ownerDao.findOwnerActive();
	}
}
