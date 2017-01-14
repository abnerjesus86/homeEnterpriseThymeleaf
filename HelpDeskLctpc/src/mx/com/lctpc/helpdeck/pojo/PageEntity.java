package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name="PAGE_ENTITY",  schema="APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "paenPageId"})
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, property = "paenId")
public class PageEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PAEN_ID" )
	@SequenceGenerator( name = "PAEN_ID", sequenceName = "SQ_PAEN_ID", allocationSize = 1 )
	@Column( name = "PAEN_ID" )
	private BigDecimal g_paenId;
	@ManyToOne( cascade = {CascadeType.REFRESH})
	@JoinColumn(name="PAEN_ENTT_ID")
	private AEntities g_paenEnttId;
	@ManyToOne( cascade = {CascadeType.REFRESH})
	@JoinColumn(name="PAEN_PAGE_ID")
	private Page g_paenPageId;
	@Column( name = "PAEN_ACTIVE" )
	private boolean		g_paenActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PAEN_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_paenCreatedDate;
	@Column( name = "PAEN_CREATED_BY", insertable = true, updatable = false )
	private String		g_paenCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PAEN_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_paenUpdateDate;
	@Column( name = "PAEN_UPDATE_BY", insertable = true, updatable = true )
	private String		g_paenUpdateBy;
	
	/**
	 * @return the paenId
	 */
	public BigDecimal getPaenId() {
		return this.g_paenId;
	}
	
	/**
	 * @param p_paenId the paenId to set
	 */
	public void setPaenId( BigDecimal p_paenId ) {
		this.g_paenId = p_paenId;
	}
	
	/**
	 * @return the paenEnttId
	 */
	@JsonIgnore
	public AEntities getPaenEnttId() {
		return this.g_paenEnttId;
	}
	
	/**
	 * @param p_paenEnttId the paenEnttId to set
	 */
	@JsonProperty
	public void setPaenEnttId( AEntities p_paenEnttId ) {
		this.g_paenEnttId = p_paenEnttId;
	}
	/**
	 * @return the paenPageId
	 */
	public Page getPaenPageId() {
		return this.g_paenPageId;
	}
	/**
	 * @param p_paenPageId the paenPageId to set
	 */
	public void setPaenPageId( Page p_paenPageId ) {
		this.g_paenPageId = p_paenPageId;
	}
	/**
	 * @return the paenActive
	 */
	public boolean isPaenActive() {
		return this.g_paenActive;
	}
	/**
	 * @param p_paenActive the paenActive to set
	 */
	public void setPaenActive( boolean p_paenActive ) {
		this.g_paenActive = p_paenActive;
	}
	/**
	 * @return the paenCreatedDate
	 */
	public Timestamp getPaenCreatedDate() {
		return this.g_paenCreatedDate;
	}
	/**
	 * @param p_paenCreatedDate the paenCreatedDate to set
	 */
	public void setPaenCreatedDate( Timestamp p_paenCreatedDate ) {
		this.g_paenCreatedDate = p_paenCreatedDate;
	}
	/**
	 * @return the paenCreatedBy
	 */
	public String getPaenCreatedBy() {
		return this.g_paenCreatedBy;
	}
	/**
	 * @param p_paenCreatedBy the paenCreatedBy to set
	 */
	public void setPaenCreatedBy( String p_paenCreatedBy ) {
		this.g_paenCreatedBy = p_paenCreatedBy;
	}
	/**
	 * @return the paenUpdateDate
	 */
	public Timestamp getPaenUpdateDate() {
		return this.g_paenUpdateDate;
	}
	/**
	 * @param p_paenUpdateDate the paenUpdateDate to set
	 */
	public void setPaenUpdateDate( Timestamp p_paenUpdateDate ) {
		this.g_paenUpdateDate = p_paenUpdateDate;
	}
	/**
	 * @return the paenUpdateBy
	 */
	public String getPaenUpdateBy() {
		return this.g_paenUpdateBy;
	}
	/**
	 * @param p_paenUpdateBy the paenUpdateBy to set
	 */
	public void setPaenUpdateBy( String p_paenUpdateBy ) {
		this.g_paenUpdateBy = p_paenUpdateBy;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageEntity [g_paenId=" + this.g_paenId + ", g_paenEnttId=" + this.g_paenEnttId + ", g_paenPageId=" + this.g_paenPageId + ", g_paenActive=" + this.g_paenActive + ", g_paenCreatedDate="
				+ this.g_paenCreatedDate + ", g_paenCreatedBy=" + this.g_paenCreatedBy + ", g_paenUpdateDate=" + this.g_paenUpdateDate + ", g_paenUpdateBy=" + this.g_paenUpdateBy + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_paenEnttId == null) ? 0 : this.g_paenEnttId.hashCode());
		result = prime * result + ((this.g_paenId == null) ? 0 : this.g_paenId.hashCode());
		result = prime * result + ((this.g_paenPageId == null) ? 0 : this.g_paenPageId.hashCode());
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
		if (!(obj instanceof PageEntity))
			return false;
		PageEntity other = (PageEntity) obj;
		if (this.g_paenEnttId == null) {
			if (other.g_paenEnttId != null)
				return false;
		} else if (!this.g_paenEnttId.equals(other.g_paenEnttId))
			return false;
		if (this.g_paenId == null) {
			if (other.g_paenId != null)
				return false;
		} else if (!this.g_paenId.equals(other.g_paenId))
			return false;
		if (this.g_paenPageId == null) {
			if (other.g_paenPageId != null)
				return false;
		} else if (!this.g_paenPageId.equals(other.g_paenPageId))
			return false;
		return true;
	}
	
	
	
	
	
	
}
