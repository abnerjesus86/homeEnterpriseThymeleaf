package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.com.lctpc.helpdeck.pojo.Platform;

public interface PlatformDao {
	public List<Platform> findAllPlatform();
	public Map<BigDecimal, String> findAllPlatformActive();
	public void savePlatform(Platform p_plfmId);
	public void updatePlatform(Platform p_plfmId);
	public Platform findPlatformById(BigDecimal p_plfmId);
}
