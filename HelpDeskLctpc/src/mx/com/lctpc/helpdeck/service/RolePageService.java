package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.RolePageDao;
import mx.com.lctpc.helpdeck.pojo.RolePage;


@Service
public class RolePageService {
	
	@Autowired
	private RolePageDao rolePageDao;
	
	public void saveOrUpdate( RolePage p_rolePage ){
		
		if(p_rolePage.getRopaId() == null || findRolePageById(p_rolePage.getRopaId() )==null){
			System.out.println("ENTRO A SAVE ROLE PAGE ");
			rolePageDao.save(p_rolePage);
		}else{
			System.out.println("ENTRO A UPDATE ROLE PAGE ");
			rolePageDao.update(p_rolePage);
		}
		
	}
	
	public void update( RolePage p_rolePage ) {
		rolePageDao.update(p_rolePage);
	}

	public List<RolePage> findAllRolePage() {
		return rolePageDao.findAllRolePage();
	}

	public RolePage findRolePageById(BigDecimal p_ropaId) {
		return rolePageDao.findRolePageById(p_ropaId);
	}

	public List<RolePage> findRolesPagesActiveFromApplicationById( BigDecimal p_appId ){
		return rolePageDao.findRolesPagesActiveFromApplicationById(p_appId);
	}
	
	public void delete( RolePage p_rolePage ) {
		rolePageDao.delete(p_rolePage);
	}
	
}
