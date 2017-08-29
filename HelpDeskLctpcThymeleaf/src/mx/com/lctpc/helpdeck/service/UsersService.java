package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.UserDao;
import mx.com.lctpc.helpdeck.dao.VEmpDao;
import mx.com.lctpc.helpdeck.pojo.AccountInformation;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;
import mx.com.lctpc.helpdeck.pojo.VEmp;

@Service
public class UsersService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VEmpDao vEmpDao;
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	public List<User> findAll() {
		return userDao.findAllUsers();
	}

	public void saveOrUpdateUser( User p_user ) {
		
		if(findUserById(p_user.getUserId())==null){
			
			userDao.saveUser(p_user);
		}else{
			
			userDao.mergeUser(p_user);
			//userDao.updateUser(p_user);
		}
		
	}

	public void persistsUser( User p_user ) {
		userDao.persistsUser(p_user);
	}

	public User findUserById( BigDecimal p_userId ) {

		return userDao.findUserById(p_userId);
	}

	public List<UserRole> findRolesFromUserById( BigDecimal p_userId ) {
		return userDao.findRolesFromUserById(p_userId);
	}
	
	public List<UserApplication> findApplicationFromUserById( BigDecimal p_userId ){
		return userDao.findApplicationFromUserById(p_userId);
	}
	
	public void deleteUser(User p_user){
		p_user.setUserActive(false);
		userDao.deleteUser(p_user);
	}
	
	public List<VEmp> findAllEmps(){
		return vEmpDao.findAllEmps();
	}
	
	public VEmp findEmp(String p_Compania, String p_Emp){
		return vEmpDao.findEmp(p_Compania, p_Emp);
	}
	
	
	public List<UserRole> existsRolesUserByIds( BigDecimal p_userId, BigDecimal p_roleId ){
		return userDao.existsRolesUserByIds(p_userId, p_roleId);
	}
	
	public List<UserApplication> existsAppnsUserByIds( BigDecimal p_userId, BigDecimal p_appId ){
		return userDao.existsAppnsUserByIds(p_userId, p_appId);
	}
	
}
