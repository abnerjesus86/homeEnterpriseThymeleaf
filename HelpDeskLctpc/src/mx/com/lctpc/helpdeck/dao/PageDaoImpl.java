package mx.com.lctpc.helpdeck.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lctpc.helpdeck.pojo.Page;
import mx.com.lctpc.helpdeck.pojo.PageEntity;

@Transactional
@Repository
public class PageDaoImpl implements PageDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Page> findAllPage() {
		// TODO Auto-generated method stub
		Query<Page> l_query = getSession().createQuery("FROM Page", Page.class);
		return l_query.getResultList();
	}

	@Override
	public void savePage( Page p_page ) {
		// TODO Auto-generated method stub
		getSession().save(p_page);
	}

	@Override
	public void updatePage( Page p_page ) {
		// TODO Auto-generated method stub
		getSession().update(p_page);
	}

	@Override
	public Page findPageById( BigDecimal p_pageId ) {
		// TODO Auto-generated method stub
		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Page> l_crtQuery = l_builder.createQuery(Page.class);
		Root<Page> l_rootUser = l_crtQuery.from(Page.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_pageId"), p_pageId));

		return (Page) getSession().createQuery(l_crtQuery).getSingleResult();

	}

	@Override
	public void deletePage( Page p_page ) {
		// TODO Auto-generated method stub
		getSession().update(p_page);
	}

	@Override
	public List<PageEntity> findEntitiesFromPage( BigDecimal p_pageId ) {
		// TODO Auto-generated method stub
		Query<PageEntity> l_queryPageEntity = getSession().createQuery("SELECT pagEnt FROM PageEntity pagEnt where pagEnt.g_paenPageId.g_pageId = :p_pageId ", PageEntity.class).setParameter("p_pageId",
				p_pageId);
		return l_queryPageEntity.getResultList();
	}
	
	@Override
	public List<PageEntity> findEntitiesActiveFromPage( BigDecimal p_pageId ) {
		// TODO Auto-generated method stub
		Query<PageEntity> l_queryPageEntity = getSession().createQuery("SELECT pagEnt FROM PageEntity pagEnt where pagEnt.g_paenPageId.g_pageId = :p_pageId and pagEnt.g_paenActive = :p", PageEntity.class)
				.setParameter("p_pageId", p_pageId)
				.setParameter("p", true)
				;
		return l_queryPageEntity.getResultList();
	}
	
	@Override
	public List<PageEntity> findEntitiesActiveLeftJoinFromPage( BigDecimal p_pageId ) {

		Query<PageEntity> l_queryPageEntity = getSession().createSQLQuery("select  * "
				+" FROM    PAGE_ENTITY PAGENT"
				+" RIGHT JOIN ENTITY ENT"
				+" ON ENT.ENTT_ID = PAGENT.PAEN_ENTT_ID AND PAGENT.PAEN_PAGE_ID = :p_pageId"
				+" WHERE   ENT.ENTT_ACTIVE = :p")
				.setParameter("p_pageId", p_pageId)
				.setParameter("p", true)
				;
		return l_queryPageEntity.getResultList();
	}

	@Override
	public PageEntity findPageEntityById( BigDecimal p_pagEntId ) {

		CriteriaBuilder l_builder = getSession().getCriteriaBuilder();
		CriteriaQuery<PageEntity> l_crtQuery = l_builder.createQuery(PageEntity.class);
		Root<PageEntity> l_rootUser = l_crtQuery.from(PageEntity.class);
		l_crtQuery.select(l_rootUser);
		l_crtQuery.where(l_builder.equal(l_rootUser.get("g_paenId"), p_pagEntId));

		return (PageEntity) getSession().createQuery(l_crtQuery).getSingleResult();
	}

	@Override
	public void deletePageEntity( PageEntity p_pagEntId ) {
		// TODO Auto-generated method stub
		getSession().update(p_pagEntId);
	}

	@Override
	public void savePageEntity( PageEntity p_pagEnt ) {
		
		getSession().save(p_pagEnt);
	}
	
	/*
	 * private NamedParameterJdbcTemplate jdbcTemplate;
	 * @Autowired
	 * private void setDataSource( DataSource p_dataSource ) {
	 * this.jdbcTemplate = new NamedParameterJdbcTemplate(p_dataSource);
	 * }
	 * @Override
	 * public List<Page> findAllPage() {
	 * // TODO Auto-generated method stub
	 * return jdbcTemplate.query("SELECT * FROM application_manager.APPLICATIONS", new RowMapper<Page>() {
	 * @Override
	 * public Page mapRow( ResultSet rs, int rowNum ) throws SQLException {
	 * // TODO Auto-generated method stub
	 * Page l_pag = new Page();
	 * l_pag.setPageId(rs.getBigDecimal("PAGE_ID"));
	 * //l_pag.setPagePageId(rs.getBigDecimal("PAGE_PAGE_ID"));
	 * l_pag.setPageName(rs.getString("PAGE_NAME"));
	 * l_pag.setPageDescription(rs.getString("PAGE_DESCRIPTION"));
	 * l_pag.setPageUrl(rs.getString("PAGE_URL"));
	 * l_pag.setPageUrl(rs.getString("PAGE_TYPE"));
	 * l_pag.setPageActive(rs.getBoolean("PAGE_ACTIVE"));
	 * l_pag.setPageCreatedDate(rs.getTimestamp("PAGE_CREATED_DATE"));
	 * l_pag.setPageCreatedBy(rs.getString("PAGE_CREATED_BY"));
	 * l_pag.setPageUpdateDate(rs.getTimestamp("PAGE_UDPATE_DATE"));
	 * l_pag.setPageUpdateBy(rs.getString("PAGE_UPDATE_BY"));
	 * return l_pag;
	 * }
	 * });
	 * }
	 * @Override
	 * public boolean savePage( Page p_page ) {
	 * // TODO Auto-generated method stub
	 * MapSqlParameterSource l_paramMap = new MapSqlParameterSource();
	 * l_paramMap.addValue("v0", p_page.getPageId());
	 * l_paramMap.addValue("v1", p_page.getPagePageId().getPageId());
	 * l_paramMap.addValue("v2", p_page.getPageName());
	 * l_paramMap.addValue("v3", p_page.getPageDescription());
	 * l_paramMap.addValue("v4", p_page.isPageActive());
	 * l_paramMap.addValue("v5", p_page.getPageCreatedDate());
	 * l_paramMap.addValue("v6", p_page.getPageCreatedBy());
	 * l_paramMap.addValue("v7", p_page.getPageUpdateDate());
	 * l_paramMap.addValue("v8", p_page.getPageUpdateBy());
	 * return jdbcTemplate.update("INSERT INTO PAGES ( PAGE_ID, PAGE_PAGE_ID, PAGE_NAME, PAGE_DESCRIPTION,"
	 * + " PAGE_URL, PAGE_TYPE, PAGE_ACTIVE, PAGE_CREATED_DATE, PAGE_CREATED_BY, PAGE_UDPATE_DATE,"
	 * + " PAGE_UPDATE_BY )"
	 * + " VALUES ( :v0, :v1, :v2, :v3, :v4, :v5, :v6, :v7, :v8, :v9, :v10 )", l_paramMap) == 1;
	 * }
	 * @Override
	 * public boolean updatePage( Page p_page ) {
	 * // TODO Auto-generated method stub
	 * return false;
	 * }
	 */
}
