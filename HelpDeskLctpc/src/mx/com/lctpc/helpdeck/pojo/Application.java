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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "APPLICATION", schema = "APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "userApplications", "appnRoles", "applicationsMaster", "appnAppnId" })
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "appnId")
public class Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * APPN_ID NOT NULL NUMBER APPN_NAME NOT NULL VARCHAR2(150) APPN_DESCRIPTION
	 * NOT NULL VARCHAR2(250) APPN_URL VARCHAR2(400) APPN_ACTIVE NOT NULL
	 * NUMBER(1) APPN_CREATED_DATE NOT NULL DATE APPN_CREATED_BY NOT NULL
	 * VARCHAR2(150) APPN_UPDATE_DATE DATE APPN_UPDATE_BY VARCHAR2(150)
	 */
	@Id
	@Column( name = "APPN_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "APPS_SEQ" )
	@SequenceGenerator( name = "APPS_SEQ", sequenceName = "SQ_APNS_ID", allocationSize = 1 )
	private BigDecimal				g_appnId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn( name="APPN_APPN_ID")
	private Application g_appnAppnId;
	/*@Column(name="APPN_APPN_ID")
	private BigDecimal g_appnAppnId;*/
	/*@ManyToOne
	@JoinColumn(name = "APPN_PLFM_ID")*/
	@Column( name = "APPN_PLFM_ID" )
	private BigDecimal g_appnPlfmId;
	@Column( name = "APPN_NAME" )
	private String					g_appnName;
	@Column( name = "APPN_DESCRIPTION" )
	private String					g_appnDescription;
	@Column( name = "APPN_URL" )
	private String					g_appnUrl;
	@Column( name = "APPN_ACTIVE" )
	private boolean					g_appnActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "APPN_CREATED_DATE", insertable = true, updatable = false )
	private Date					g_appnCreatedDate;
	@Column( name = "APPN_CREATED_BY", insertable = true, updatable = false )
	private String					g_appnCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "APPN_UPDATE_DATE", insertable = true, updatable = true )
	private Date					g_appnUpdateDate;
	@Column( name = "APPN_UPDATE_BY", insertable = true, updatable = true )
	private String					g_appnUpdateBy;
	@OneToMany( mappedBy = "g_usapAppnId" )
	private List<UserApplication>	g_userApplications;
	@OneToMany( mappedBy = "g_aproAppnId" )
	private List<ApplicationRole>	g_appnRoles;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "g_appnAppnId")
	private List<Application> g_applicationsMaster = new ArrayList<Application>();
	
	/**
	 * @return the appnId
	 */
	public BigDecimal getAppnId() {
		return this.g_appnId;
	}

	/**
	 * @param p_appnId
	 *            the appnId to set
	 */
	public void setAppnId( BigDecimal p_appnId ) {
		this.g_appnId = p_appnId;
	}
	
	/**
	 * @return the appnAppnId
	 */
	public Application getAppnAppnId() {
		return this.g_appnAppnId;
	}

	/**
	 * @param p_appnAppnId the appnAppnId to set
	 */
	public void setAppnAppnId( Application p_appnAppnId ) {
		this.g_appnAppnId = p_appnAppnId;
	}

	/**
	 * @return the appnName
	 */
	public String getAppnName() {
		return this.g_appnName;
	}

	/**
	 * @param p_appnName
	 *            the appnName to set
	 */
	public void setAppnName( String p_appnName ) {
		this.g_appnName = p_appnName;
	}

	/**
	 * @return the appnDescription
	 */
	public String getAppnDescription() {
		return this.g_appnDescription;
	}

	/**
	 * @param p_appnDescription
	 *            the appnDescription to set
	 */
	public void setAppnDescription( String p_appnDescription ) {
		this.g_appnDescription = p_appnDescription;
	}

	/**
	 * @return the appnUrl
	 */
	public String getAppnUrl() {
		return this.g_appnUrl;
	}

	/**
	 * @param p_appnUrl
	 *            the appnUrl to set
	 */
	public void setAppnUrl( String p_appnUrl ) {
		this.g_appnUrl = p_appnUrl;
	}

	/**
	 * @return the appnActive
	 */
	public boolean getAppnActive() {
		return this.g_appnActive;
	}

	/**
	 * @param p_appnActive
	 *            the appnActive to set
	 */
	public void setAppnActive( boolean p_appnActive ) {
		this.g_appnActive = p_appnActive;
	}

	/**
	 * @return the appnCreatedDate
	 */
	public Date getAppnCreatedDate() {
		return this.g_appnCreatedDate;
	}

	/**
	 * @param p_appnCreatedDate
	 *            the appnCreatedDate to set
	 */
	public void setAppnCreatedDate( Date p_appnCreatedDate ) {
		this.g_appnCreatedDate = p_appnCreatedDate;
	}

	/**
	 * @return the appnCreatedBy
	 */
	public String getAppnCreatedBy() {
		return this.g_appnCreatedBy;
	}

	/**
	 * @param p_appnCreatedBy
	 *            the appnCreatedBy to set
	 */
	public void setAppnCreatedBy( String p_appnCreatedBy ) {
		this.g_appnCreatedBy = p_appnCreatedBy;
	}

	/**
	 * @return the appnUpdateDate
	 */
	public Date getAppnUpdateDate() {
		return this.g_appnUpdateDate;
	}

	/**
	 * @param p_appnUpdateDate
	 *            the appnUpdateDate to set
	 */
	public void setAppnUpdateDate( Date p_appnUpdateDate ) {
		this.g_appnUpdateDate = p_appnUpdateDate;
	}

	/**
	 * @return the appnUpdateBy
	 */
	public String getAppnUpdateBy() {
		return this.g_appnUpdateBy;
	}

	/**
	 * @param p_appnUpdateBy
	 *            the appnUpdateBy to set
	 */
	public void setAppnUpdateBy( String p_appnUpdateBy ) {
		this.g_appnUpdateBy = p_appnUpdateBy;
	}

	/**
	 * @return the userApplications
	 */
	public List<UserApplication> getUserApplications() {
		return this.g_userApplications;
	}

	/**
	 * @param p_userApplications
	 *            the userApplications to set
	 */
	public void setUserApplications( List<UserApplication> p_userApplications ) {
		this.g_userApplications = p_userApplications;
	}

	/**
	 * @return the appnRoles
	 */
	public List<ApplicationRole> getAppnRoles() {
		return this.g_appnRoles;
	}

	/**
	 * @param p_appnRoles
	 *            the appnRoles to set
	 */
	public void setAppnRoles( List<ApplicationRole> p_appnRoles ) {
		this.g_appnRoles = p_appnRoles;
	}

	/**
	 * @return the appnPlfmId
	 */
	public BigDecimal getAppnPlfmId() {
		return this.g_appnPlfmId;
	}

	/**
	 * @param p_appnPlfmId the appnPlfmId to set
	 */
	public void setAppnPlfmId( BigDecimal p_appnPlfmId ) {
		this.g_appnPlfmId = p_appnPlfmId;
	}

	/**
	 * @return the applicationsMaster
	 */
	public List<Application> getApplicationsMaster() {
		return this.g_applicationsMaster;
	}

	/**
	 * @param p_applicationsMaster the applicationsMaster to set
	 */
	public void setApplicationsMaster( List<Application> p_applicationsMaster ) {
		this.g_applicationsMaster = p_applicationsMaster;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_appnId == null) ? 0 : this.g_appnId.hashCode());
		result = prime * result + ((this.g_appnName == null) ? 0 : this.g_appnName.hashCode());
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
		Application other = (Application) obj;
		if (this.g_appnId == null) {
			if (other.g_appnId != null)
				return false;
		} else if (!this.g_appnId.equals(other.g_appnId))
			return false;
		if (this.g_appnName == null) {
			if (other.g_appnName != null)
				return false;
		} else if (!this.g_appnName.equals(other.g_appnName))
			return false;
		return true;
	}
	
	
}
