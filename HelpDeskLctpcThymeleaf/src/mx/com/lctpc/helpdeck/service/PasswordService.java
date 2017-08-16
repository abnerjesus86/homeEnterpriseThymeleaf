package mx.com.lctpc.helpdeck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.PasswordDao;

@Service
public class PasswordService {
	
	@Autowired
	private PasswordDao g_passDao;
	
	public String resetPassword(String p_username, String p_passwordNew, boolean p_reqNewPass) throws Exception{
		return g_passDao.resetPassword(p_username, p_passwordNew, p_reqNewPass);
	}
	
	public String changePassword(String p_username, String p_passwordCurrent, String p_passwordNew) throws Exception{
		return g_passDao.changePassword(p_username, p_passwordCurrent, p_passwordNew);
	}
}
