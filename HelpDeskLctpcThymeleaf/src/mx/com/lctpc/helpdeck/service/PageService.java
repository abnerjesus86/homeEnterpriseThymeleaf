package mx.com.lctpc.helpdeck.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lctpc.helpdeck.dao.PageDao;
import mx.com.lctpc.helpdeck.pojo.Application;
import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;

@Service
public class PageService {
	
	@Autowired
	private PageDao pageDao;
	
	public List<Page> findAllPage(){
		return pageDao.findAllPage();
	}
	
	public void saveOrUpdatePage(Page p_page){
		
		if(p_page.getPageId() == null || findPageById(p_page.getPageId())==null){
			System.out.println("ENTRO A SAVE PAGE ");
			pageDao.savePage(p_page);
		}else{
			System.out.println("ENTRO A UPDATE PAGE ");
			pageDao.updatePage(p_page);
		}
		
	}
	
	public Page findPageById(BigDecimal p_pageId){
		return pageDao.findPageById(p_pageId);
	}
	
	public void deletePage(Page p_page){
		p_page.setPageActive(false);
		pageDao.deletePage(p_page);
	}
	
	public List<PageEntity> findEntitiesFromPage( BigDecimal p_pageId ){
		return pageDao.findEntitiesFromPage(p_pageId);
	}
	
	public List<PageEntity> findEntitiesActiveFromPage( BigDecimal p_pageId ){
		return pageDao.findEntitiesActiveFromPage(p_pageId);
	}
	
	public List<PageEntity> findEntitiesActiveLeftJoinFromPage( BigDecimal p_pageId ){
		return pageDao.findEntitiesActiveLeftJoinFromPage(p_pageId);
	}
	
	public PageEntity findPageEntityById( BigDecimal p_pagEntId ){
		return pageDao.findPageEntityById(p_pagEntId);
	}
	
	public void deletePageEntity(PageEntity p_pagEnt){
		p_pagEnt.setPaenActive(false);
		pageDao.deletePageEntity(p_pagEnt);
	}
	
	public void savePageEntity(PageEntity p_pagEnt){
		pageDao.savePageEntity(p_pagEnt);
	}
	
	public void deletePageFromApplicationById(Page p_page, Application p_appnId){
		pageDao.deletePageFromApplicationById(p_page, p_appnId);
	}
	
	public List<Page> findPageFromApplicationById(BigDecimal p_appnId){
		return pageDao.findPageFromApplicationById(p_appnId);
	}
	
	public PageEntity findPageEntity( BigDecimal p_pag, BigDecimal p_Ent ){
		return null;// pageDao.findPageEntityById(p_pagEntId);
	}
}
