package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;

public interface PageDao {
	public List<Page> findAllPage();
	public void savePage(Page p_page);
	public void updatePage(Page p_page);
	public Page findPageById(BigDecimal p_pageId);
	public void deletePage(Page p_page);
	public List<PageEntity> findEntitiesFromPage(BigDecimal p_pageId);
	public List<PageEntity> findEntitiesActiveFromPage(BigDecimal p_pageId);
	public List<PageEntity> findEntitiesActiveLeftJoinFromPage( BigDecimal p_pageId );
	public PageEntity findPageEntityById(BigDecimal p_pagEntId);
	public void deletePageEntity(PageEntity p_pagEntId);
	public void savePageEntity(PageEntity p_pagEnt);
	public void deletePageFromApplicationById(Page p_page, BigDecimal p_appnId);
	public List<Page> findPageFromApplicationById(BigDecimal p_appnId);
	
	
}
