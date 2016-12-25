package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.UserDao;
import mx.com.lctpc.helpdeck.pojo.User;
import mx.com.lctpc.helpdeck.pojo.UserApplication;
import mx.com.lctpc.helpdeck.pojo.UserRole;

@Service
public class UsersService {

	@Autowired
	private UserDao userDao;

	public List<User> findAll() {
		return userDao.findAllUsers();
	}

	public void saveOrUpdateUser( User p_user ) {
		
		if(findUserById(p_user.getUserId())==null){
			
			userDao.saveUser(p_user);
		}else{
			
			userDao.updateUser(p_user);
		}
		
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
	
}
