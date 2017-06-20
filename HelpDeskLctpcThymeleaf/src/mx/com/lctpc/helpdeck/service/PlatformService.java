package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.PlatformDao;
import mx.com.lctpc.helpdeck.pojo.Platform;

@Service
public class PlatformService {
	
	@Autowired
	private PlatformDao pltfDao;
	
	public List<Platform> findAllPlatform(){
		return pltfDao.findAllPlatform();
	}
	
	public Map<BigDecimal, String> findAllPlatformActive(){
		return pltfDao.findAllPlatformActive();
	}
	
	public Platform findPlatformById(BigDecimal p_plfmId){
		return findPlatformById(p_plfmId);
	}
	
	public void savePlatform(Platform p_plfmId){
		pltfDao.savePlatform(p_plfmId);
	}
	
	public void updatePlatform(Platform p_plfmId){
		pltfDao.updatePlatform(p_plfmId);
	}
	
	
	
}
