package mx.com.lctpc.helpdeck.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "PERMISSION", schema = "APPLICATION_MANAGER" )
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "prmnId",scope = Permission.class)
public class Permission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	/*
	 * PRMN_ID NOT NULL NUMBER PRMN_NAME NOT NULL VARCHAR2(30) PRMN_DESCRIPTION
	 * NOT NULL VARCHAR2(150) PRMN_ACTIVE NOT NULL NUMBER(1) PRMN_CREATED_DATE
	 * NOT NULL DATE PRMN_CREATED_BY NOT NULL VARCHAR2(150) PRMN_UPDATE_DATE
	 * DATE PRMN_UPDATE_BY VARCHAR2(150)
	 */
	@Id
	@Column( name = "PRMN_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PER_SEQ" )
	@SequenceGenerator( name = "PER_SEQ", sequenceName = "SQ_PRMS_ID", allocationSize = 1 )
	private BigDecimal	g_prmnId;
	@Column( name = "PRMN_NAME" )
	private String		g_prmnName;
	@Column( name = "PRMN_DESCRIPTION" )
	private String		g_prmnDescription;
	@Column( name = "PRMN_ACTIVE" )
	private boolean		g_prmnActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PRMN_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_prmnCreatedDate;
	@Column( name = "PRMN_CREATED_BY", insertable = true, updatable = false )
	private String		g_prmnCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "PRMN_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_prmnUpdateDate;
	@Column( name = "PRMN_UPDATE_BY", insertable = true, updatable = true )
	private String		g_prmnUpdateBy;

	/**
	 * @return the prmnId
	 */
	public BigDecimal getPrmnId() {
		return this.g_prmnId;
	}

	/**
	 * @param p_prmnId
	 *            the prmnId to set
	 */
	public void setPrmnId( BigDecimal p_prmnId ) {
		this.g_prmnId = p_prmnId;
	}

	/**
	 * @return the prmnName
	 */
	public String getPrmnName() {
		return this.g_prmnName;
	}

	/**
	 * @param p_prmnName
	 *            the prmnName to set
	 */
	public void setPrmnName( String p_prmnName ) {
		this.g_prmnName = p_prmnName;
	}

	/**
	 * @return the prmnDescription
	 */
	public String getPrmnDescription() {
		return this.g_prmnDescription;
	}

	/**
	 * @param p_prmnDescription
	 *            the prmnDescription to set
	 */
	public void setprmnDescription( String p_prmnDescription ) {
		this.g_prmnDescription = p_prmnDescription;
	}

	/**
	 * @return the prmnActive
	 */
	public boolean isPrmnActive() {
		return this.g_prmnActive;
	}

	/**
	 * @param p_prmnActive
	 *            the prmnActive to set
	 */
	public void setPrmnActive( boolean p_prmnActive ) {
		this.g_prmnActive = p_prmnActive;
	}

	/**
	 * @return the prmnCreatedDate
	 */
	public Timestamp getPrmnCreatedDate() {
		return this.g_prmnCreatedDate;
	}

	/**
	 * @param p_prmnCreatedDate
	 *            the prmnCreatedDate to set
	 */
	public void setPrmnCreatedDate( Timestamp p_prmnCreatedDate ) {
		this.g_prmnCreatedDate = p_prmnCreatedDate;
	}

	/**
	 * @return the prmnCreatedBy
	 */
	public String getPrmnCreatedBy() {
		return this.g_prmnCreatedBy;
	}

	/**
	 * @param p_prmnCreatedBy
	 *            the prmnCreatedBy to set
	 */
	public void setPrmnCreatedBy( String p_prmnCreatedBy ) {
		this.g_prmnCreatedBy = p_prmnCreatedBy;
	}

	/**
	 * @return the prmnUpdateDate
	 */
	public Timestamp getPrmnUpdateDate() {
		return this.g_prmnUpdateDate;
	}

	/**
	 * @param p_prmnUpdateDate
	 *            the prmnUpdateDate to set
	 */
	public void setPrmnUpdateDate( Timestamp p_prmnUpdateDate ) {
		this.g_prmnUpdateDate = p_prmnUpdateDate;
	}

	/**
	 * @return the prmnUpdateBy
	 */
	public String getPrmnUpdateBy() {
		return this.g_prmnUpdateBy;
	}

	/**
	 * @param p_prmnUpdateBy
	 *            the prmnUpdateBy to set
	 */
	public void setPrmnUpdateBy( String p_prmnUpdateBy ) {
		this.g_prmnUpdateBy = p_prmnUpdateBy;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_prmnId == null) ? 0 : this.g_prmnId.hashCode());
		result = prime * result + ((this.g_prmnName == null) ? 0 : this.g_prmnName.hashCode());
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
		if (!(obj instanceof Permission))
			return false;
		Permission other = (Permission) obj;
		if (this.g_prmnId == null) {
			if (other.g_prmnId != null)
				return false;
		} else if (!this.g_prmnId.equals(other.g_prmnId))
			return false;
		if (this.g_prmnName == null) {
			if (other.g_prmnName != null)
				return false;
		} else if (!this.g_prmnName.equals(other.g_prmnName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Permission [g_prmnId=" + this.g_prmnId + ", g_prmnName=" + this.g_prmnName + ", g_rpmnDescription="
				+ this.g_prmnDescription + ", g_prmnActive=" + this.g_prmnActive + ", g_prmnCreatedDate="
				+ this.g_prmnCreatedDate + ", g_prmnCreatedBy=" + this.g_prmnCreatedBy + ", g_prmnUpdateDate="
				+ this.g_prmnUpdateDate + ", g_prmnUpdateBy=" + this.g_prmnUpdateBy + "]";
	}

}
