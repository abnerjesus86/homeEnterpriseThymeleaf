package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "PAGE", schema = "APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "pageEntities", "pagePageId", "pageMaster", "entities" })
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "pageId")
public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * PAGE_ID NOT NULL NUMBER PAGE_PAGE_ID NUMBER(10) PAGE_NAME NOT NULL
	 * VARCHAR2(150) PAGE_DESCRIPTION NOT NULL VARCHAR2(250) PAGE_URL
	 * VARCHAR2(1500) PAGE_TYPE NOT NULL NUMBER(1) PAGE_ACTIVE NOT NULL
	 * NUMBER(1) PAGE_CREATED_DATE NOT NULL DATE PAGE_CREATED_BY NOT NULL
	 * VARCHAR2(150) PAGE_UDPATE_DATE DATE PAGE_UPDATE_BY VARCHAR2(150)
	 */
	@Id
	@Column( name = "PAGE_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PAGE_SEQ" )
	@SequenceGenerator( name = "PAGE_SEQ", sequenceName = "SQ_PAGE_ID", allocationSize = 1 )
	private BigDecimal	g_pageId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn( name = "PAGE_PAGE_ID" )
	private Page		g_pagePageId;
	@Column( name = "PAGE_DISPLAY" )
	private String		g_pageDisplay;
	/*@Column( name = "PAGE_NOMENCLATURE" )
	private String		g_pageNomenclature;*/
	@Column( name = "PAGE_DESCRIPTION" )
	private String		g_pageDescription;
	@Column( name = "PAGE_URL" )
	private String		g_pageUrl;
	@Column( name = "PAGE_TYPE" )
	private short		g_pageType;
	@Column( name = "PAGE_ACTIVE" )
	private boolean		g_pageActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PAGE_CREATED_DATE", insertable = true, updatable = false )
	private Date	g_pageCreatedDate;
	@Column( name = "PAGE_CREATED_BY", insertable = true, updatable = false )
	private String		g_pageCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PAGE_UPDATE_DATE", insertable = true, updatable = true )
	private Date	g_pageUpdateDate;
	@Column( name = "PAGE_UPDATE_BY", insertable = true, updatable = true )
	private String		g_pageUpdateBy;
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "g_paenPageId", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	private List<PageEntity> g_pageEntities = new ArrayList<PageEntity>();
	@OneToMany(  mappedBy = "g_pagePageId")
	private List<Page> g_pageMaster = new ArrayList<Page>();
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinTable(name = "PAGE_ENTITY", joinColumns = {
			@JoinColumn(name = "PAEN_PAGE_ID",nullable = false) },
			inverseJoinColumns = { @JoinColumn(name = "PAEN_ENTT_ID",nullable = false) })*/
	@Transient
	private List<AEntities> g_entities = new ArrayList<AEntities>();
	
	/**
	 * @return the pageId
	 */
	public BigDecimal getPageId() {
		return this.g_pageId;
	}

	/**
	 * @param p_pageId
	 *            the pageId to set
	 */
	public void setPageId( BigDecimal p_pageId ) {
		this.g_pageId = p_pageId;
	}

	/**
	 * @return the pagePageId
	 */
	public Page getPagePageId() {
		return this.g_pagePageId;
	}

	/**
	 * @param p_pagePageId
	 *            the pagePageId to set
	 */
	public void setPagePageId( Page p_pagePageId ) {
		this.g_pagePageId = p_pagePageId;
	}

	/**
	 * @return the pageName
	 */
	public String getPageDisplay() {
		return this.g_pageDisplay;
	}

	/**
	 * @param p_pageName
	 *            the pageName to set
	 */
	public void setPageDisplay( String p_pageDisplay ) {
		this.g_pageDisplay = p_pageDisplay;
	}

	/**
	 * @return the pageNomenclature
	 *//*
	public String getPageNomenclature() {
		return this.g_pageNomenclature;
	}

	*//**
	 * @param p_pageNomenclature the pageNomenclature to set
	 *//*
	public void setPageNomenclature( String p_pageNomenclature ) {
		this.g_pageNomenclature = p_pageNomenclature;
	}
*/
	/**
	 * @return the pageDescription
	 */
	public String getPageDescription() {
		return this.g_pageDescription;
	}

	/**
	 * @param p_pageDescription
	 *            the pageDescription to set
	 */
	public void setPageDescription( String p_pageDescription ) {
		this.g_pageDescription = p_pageDescription;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return this.g_pageUrl;
	}

	/**
	 * @param p_pageUrl
	 *            the pageUrl to set
	 */
	public void setPageUrl( String p_pageUrl ) {
		this.g_pageUrl = p_pageUrl;
	}

	/**
	 * @return the pageType
	 */
	public short getPageType() {
		return this.g_pageType;
	}

	/**
	 * @param p_pageType
	 *            the pageType to set
	 */
	public void setPageType( short p_pageType ) {
		this.g_pageType = p_pageType;
	}

	/**
	 * @return the pageActive
	 */
	public boolean isPageActive() {
		return this.g_pageActive;
	}

	/**
	 * @param p_pageActive
	 *            the pageActive to set
	 */
	public void setPageActive( boolean p_pageActive ) {
		this.g_pageActive = p_pageActive;
	}

	/**
	 * @return the pageCreatedDate
	 */
	public Date getPageCreatedDate() {
		return this.g_pageCreatedDate;
	}

	/**
	 * @param p_pageCreatedDate
	 *            the pageCreatedDate to set
	 */
	public void setPageCreatedDate( Date p_pageCreatedDate ) {
		this.g_pageCreatedDate = p_pageCreatedDate;
	}

	/**
	 * @return the pageCreatedBy
	 */
	public String getPageCreatedBy() {
		return this.g_pageCreatedBy;
	}

	/**
	 * @param p_pageCreatedBy
	 *            the pageCreatedBy to set
	 */
	public void setPageCreatedBy( String p_pageCreatedBy ) {
		this.g_pageCreatedBy = p_pageCreatedBy;
	}

	/**
	 * @return the pageUpdateDate
	 */
	public Date getPageUpdateDate() {
		return this.g_pageUpdateDate;
	}

	/**
	 * @param p_pageUpdateDate
	 *            the pageUpdateDate to set
	 */
	public void setPageUpdateDate( Date p_pageUpdateDate ) {
		this.g_pageUpdateDate = p_pageUpdateDate;
	}

	/**
	 * @return the pageUpdateBy
	 */
	public String getPageUpdateBy() {
		return this.g_pageUpdateBy;
	}

	/**
	 * @param p_pageUpdateBy
	 *            the pageUpdateBy to set
	 */
	public void setPageUpdateBy( String p_pageUpdateBy ) {
		this.g_pageUpdateBy = p_pageUpdateBy;
	}

	/**
	 * @return the pageEntities
	 */
	public List<PageEntity> getPageEntities() {
		return this.g_pageEntities;
	}

	/**
	 * @param p_pageEntities the pageEntities to set
	 */
	public void setPageEntities( List<PageEntity> p_pageEntities ) {
		this.g_pageEntities = p_pageEntities;
	}

	/**
	 * @return the pageMaster
	 */
	public List<Page> getPageMaster() {
		return this.g_pageMaster;
	}

	/**
	 * @param p_pageMaster the pageMaster to set
	 */
	public void setPageMaster( List<Page> p_pageMaster ) {
		this.g_pageMaster = p_pageMaster;
	}

	
	/**
	 * @return the entities
	 */
	public List<AEntities> getEntities() {
		return this.g_entities;
	}

	/**
	 * @param p_entities the entities to set
	 */
	public void setEntities( List<AEntities> p_entities ) {
		this.g_entities = p_entities;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_pageDisplay == null) ? 0 : this.g_pageDisplay.hashCode());
		result = prime * result + ((this.g_pageId == null) ? 0 : this.g_pageId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (this.g_pageDisplay == null) {
			if (other.g_pageDisplay != null)
				return false;
		} else if (!this.g_pageDisplay.equals(other.g_pageDisplay))
			return false;
		if (this.g_pageId == null) {
			if (other.g_pageId != null)
				return false;
		} else if (!this.g_pageId.equals(other.g_pageId))
			return false;
		return true;
	}
	
	
	
}
