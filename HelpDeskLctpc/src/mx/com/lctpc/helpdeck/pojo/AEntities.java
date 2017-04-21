package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "ENTITY", schema = "APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "pageEntities" })
/*@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "enttId", scope = AEntities.class)*/
public class AEntities implements Serializable{

	/*
	 * ENTT_ID NOT NULL NUMBER ENTT_NAME NOT NULL VARCHAR2(32) ENTT_DESCRIPTION
	 * VARCHAR2(150) ENTT_ACTIVE NOT NULL NUMBER(1) ENTT_CREATED_DATE NOT NULL
	 * DATE ENTT_CREATED_BY NOT NULL VARCHAR2(150) ENTT_UPDATE_DATE DATE
	 * ENTT_UPDATE_BY VARCHAR2(150)
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column( name = "ENTT_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ENTI_SEQ" )
	@SequenceGenerator( name = "ENTI_SEQ", sequenceName = "SQ_ENES_ID", allocationSize = 1 )
	private BigDecimal	g_enttId;
	@Column( name = "ENTT_NAME" )
	private String		g_enttName;
	@Column( name = "ENTT_DESCRIPTION" )
	private String		g_enttDescription;
	@Column( name = "ENTT_ACTIVE" )
	private boolean		g_enttActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ENTT_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_enttCreatedDate;
	@Column( name = "ENTT_CREATED_BY", insertable = true, updatable = false )
	private String		g_enttCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ENTT_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_enttUpdateDate;
	@Column( name = "ENTT_UPDATE_BY", insertable = true, updatable = true )
	private String		g_enttUpdateBy;
	@OneToMany(  mappedBy = "g_paenEnttId" )
	private List<PageEntity> g_pageEntities = new ArrayList<PageEntity>();
	
	public String getIdAsString() {
		return this.g_enttId.toString();
	}
	
	/**
	 * @return the enttId
	 */
	public BigDecimal getEnttId() {
		return this.g_enttId;
	}

	/**
	 * @param p_enttId
	 *            the enttId to set
	 */
	public void setEnttId( BigDecimal p_enttId ) {
		this.g_enttId = p_enttId;
	}

	/**
	 * @return the enttName
	 */
	public String getEnttName() {
		return this.g_enttName;
	}

	/**
	 * @param p_enttName
	 *            the enttName to set
	 */
	public void setEnttName( String p_enttName ) {
		this.g_enttName = p_enttName;
	}

	/**
	 * @return the enttDescription
	 */
	public String getEnttDescription() {
		return this.g_enttDescription;
	}

	/**
	 * @param p_enttDescription
	 *            the enttDescription to set
	 */
	public void setEnttDescription( String p_enttDescription ) {
		this.g_enttDescription = p_enttDescription;
	}

	/**
	 * @return the enttActive
	 */
	public boolean isEnttActive() {
		return this.g_enttActive;
	}

	/**
	 * @param p_enttActive
	 *            the enttActive to set
	 */
	public void setEnttActive( boolean p_enttActive ) {
		this.g_enttActive = p_enttActive;
	}

	/**
	 * @return the enttCreatedDate
	 */
	public Timestamp getEnttCreatedDate() {
		return this.g_enttCreatedDate;
	}

	/**
	 * @param p_enttCreatedDate
	 *            the enttCreatedDate to set
	 */
	public void setEnttCreatedDate( Timestamp p_enttCreatedDate ) {
		this.g_enttCreatedDate = p_enttCreatedDate;
	}

	/**
	 * @return the enttCreatedBy
	 */
	public String getEnttCreatedBy() {
		return this.g_enttCreatedBy;
	}

	/**
	 * @param p_enttCreatedBy
	 *            the enttCreatedBy to set
	 */
	public void setEnttCreatedBy( String p_enttCreatedBy ) {
		this.g_enttCreatedBy = p_enttCreatedBy;
	}

	/**
	 * @return the enttUpdateDate
	 */
	public Timestamp getEnttUpdateDate() {
		return this.g_enttUpdateDate;
	}

	/**
	 * @param p_enttUpdateDate
	 *            the enttUpdateDate to set
	 */
	public void setEnttUpdateDate( Timestamp p_enttUpdateDate ) {
		this.g_enttUpdateDate = p_enttUpdateDate;
	}

	/**
	 * @return the enttUpdateBy
	 */
	public String getEnttUpdateBy() {
		return this.g_enttUpdateBy;
	}

	/**
	 * @param p_enttUpdateBy
	 *            the enttUpdateBy to set
	 */
	public void setEnttUpdateBy( String p_enttUpdateBy ) {
		this.g_enttUpdateBy = p_enttUpdateBy;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AEntities [g_enttId=" + this.g_enttId + ", g_enttName=" + this.g_enttName + ", g_enttDescription=" + this.g_enttDescription + ", g_enttActive=" + this.g_enttActive + ", g_enttCreatedDate="
				+ this.g_enttCreatedDate + ", g_enttCreatedBy=" + this.g_enttCreatedBy + ", g_enttUpdateDate=" + this.g_enttUpdateDate + ", g_enttUpdateBy=" + this.g_enttUpdateBy + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_enttId == null) ? 0 : this.g_enttId.hashCode());
		result = prime * result + ((this.g_enttName == null) ? 0 : this.g_enttName.hashCode());
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
		AEntities other = (AEntities) obj;
		if (this.g_enttId == null) {
			if (other.g_enttId != null)
				return false;
		} else if (!this.g_enttId.equals(other.g_enttId))
			return false;
		if (this.g_enttName == null) {
			if (other.g_enttName != null)
				return false;
		} else if (!this.g_enttName.equals(other.g_enttName))
			return false;
		return true;
	}

}
