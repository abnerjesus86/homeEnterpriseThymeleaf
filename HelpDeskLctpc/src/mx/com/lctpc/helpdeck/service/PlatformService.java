package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.PlatformDao;

@Service
public class PlatformService {
	
	@Autowired
	private PlatformDao pltfDao;
	
	public Map<BigDecimal, String> findAllPlatformActive(){
		return pltfDao.findAllPlatformActive();
	}
	
	
}
