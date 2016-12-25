package mx.com.lctpc.helpdeck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.ContainerEdiDao;
import mx.com.lctpc.helpdeck.pojo.ContainerEdi;

@Service
public class ContainerEdiService {
	
	@Autowired
	private ContainerEdiDao g_containerEdiDao;
	
	public boolean executeCoarri(ContainerEdi p_cntrEdi){
		
		return g_containerEdiDao.executeCoarri(p_cntrEdi);
	}
	
}
