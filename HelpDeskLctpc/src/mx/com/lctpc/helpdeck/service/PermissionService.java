package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.PermissionDao;
import mx.com.lctpc.helpdeck.pojo.Permission;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionDao permDao;
	
	public List<Permission> findAllPermisions(){
		return permDao.findAllPermisions();
	}
	
	public void saveOrUpdatePermission(Permission p_per){
		if(p_per.getPrmnId() == null || findPermissionById(p_per.getPrmnId())==null){

			permDao.savePermission(p_per);
		}else{

			permDao.updatePermission(p_per);
		}
	}
	
	public Permission findPermissionById(BigDecimal p_perId){
		return permDao.findPermissionById(p_perId);
	}
	
	public void deletePermission(Permission p_perId){
		p_perId.setPrmnActive(false);
		permDao.deletePermission(p_perId);
	}
	
}
