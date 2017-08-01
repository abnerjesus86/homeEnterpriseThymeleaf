package mx.com.lctpc.helpdeck.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PasswordDaoImpl implements PasswordDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public String resetPassword( String p_username, String p_passwordNew ) throws Exception {
		// TODO Auto-generated method stub
		StoredProcedureQuery l_querySP = getSession().createStoredProcedureQuery("PK_ACCOUNT.p_ResetPassword");
		l_querySP
		.registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_PasswordNew", String.class, ParameterMode.INOUT)
		.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		l_querySP
		.setParameter("p_User", p_username)
		.setParameter("p_PasswordNew", p_passwordNew )
		.execute();
		
		String l_message = l_querySP.getOutputParameterValue("p_Message").toString();
		Map<String, String> l_res =  new HashMap<String,String>();
		l_res.put( "message", l_message);
		l_res.put("pass", l_querySP.getOutputParameterValue("p_PasswordNew").toString());
		
		if(!l_message.equals("OK")){
			throw new Exception(l_message);
		}
		
		return l_querySP.getOutputParameterValue("p_PasswordNew").toString();
		
	}

	@Override
	public String changePassword( String p_username, String p_passwordCurrent, String p_passwordNew ) throws Exception {
		// TODO Auto-generated method stub
		
		StoredProcedureQuery l_querySP = getSession().createStoredProcedureQuery("PK_ACCOUNT.p_ChangePassword");
		l_querySP
		.registerStoredProcedureParameter("p_User", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_PasswordCurrent", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_PasswordNew", String.class, ParameterMode.IN)
		.registerStoredProcedureParameter("p_Message", String.class, ParameterMode.OUT);
		
		l_querySP
		.setParameter("p_User", p_username)
		.setParameter("p_PasswordCurrent", p_passwordCurrent )
		.setParameter("p_PasswordNew", p_passwordNew )
		.execute();
		
		String l_message = l_querySP.getOutputParameterValue("p_Message").toString();
		
		if(!l_message.equals("OK")){
			throw new Exception(l_message);
		}
		
		return l_querySP.getOutputParameterValue("p_Message").toString();
		
	}

}
