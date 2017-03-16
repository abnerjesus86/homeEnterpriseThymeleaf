package mx.com.lctpc.helpdeck.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table( name = "ROLE", schema = "APPLICATION_MANAGER" )
@JsonIgnoreProperties(value = { "roleUsers" })
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "roleId",
		  scope = Rol.class)
public class Rol {
	/*
	 * ROLE_ID NOT NULL NUMBER ROLE_NAME NOT NULL VARCHAR2(150) ROLE_DESCRIPTION
	 * NOT NULL VARCHAR2(250) ROLE_ACTIVE NOT NULL NUMBER(1) ROLE_CREATED_DATE
	 * NOT NULL DATE ROLE_CREATED_BY NOT NULL VARCHAR2(150) ROLE_UDPATE_DATE
	 * DATE ROLE_UPDATE_BY VARCHAR2(150)
	 */
	@Id
	@Column( name = "ROLE_ID" )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PAGE_SEQ" )
	@SequenceGenerator( name = "PAGE_SEQ", sequenceName = "SQ_ROLE_ID", allocationSize = 1 )
	BigDecimal				g_roleId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "ROLE_APPN_ID" )
	private Application g_roleAppnId;
	@Column( name = "ROLE_NAME" )
	String					g_roleName;
	@Column( name = "ROLE_DESCRIPTION" )
	String					g_roleDescription;
	@Column( name = "ROLE_ACTIVE" )
	boolean					g_roleActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ROLE_CREATED_DATE", insertable = true, updatable = false  )
	Date				g_roleCreatedDate;
	@Column( name = "ROLE_CREATED_BY", insertable = true, updatable = false  )
	String					g_roleCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ROLE_UPDATE_DATE", insertable = true, updatable = true )
	Date				g_roleUdpateDate;
	@Column( name = "ROLE_UPDATE_BY", insertable = true, updatable = true )
	String					g_roleUpdateBy;
	@OneToMany( mappedBy = "g_usroRolId" )
	List<UserRole>			g_roleUsers;
	/*@OneToMany( mappedBy = "g_aproRoleId" )
	List<ApplicationRole>	g_roleApplication;*/

	/**
	 * @return the roleId
	 */
	public BigDecimal getRoleId() {
		return this.g_roleId;
	}

	/**
	 * @param p_roleId
	 *            the roleId to set
	 */
	public void setRoleId( BigDecimal p_roleId ) {
		this.g_roleId = p_roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return this.g_roleName;
	}

	/**
	 * @param p_roleName
	 *            the roleName to set
	 */
	public void setRoleName( String p_roleName ) {
		this.g_roleName = p_roleName;
	}

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return this.g_roleDescription;
	}

	/**
	 * @param p_roleDescription
	 *            the roleDescription to set
	 */
	public void setRoleDescription( String p_roleDescription ) {
		this.g_roleDescription = p_roleDescription;
	}

	/**
	 * @return the roleActive
	 */
	public boolean getRoleActive() {
		return this.g_roleActive;
	}

	/**
	 * @param p_roleActive
	 *            the roleActive to set
	 */
	public void setRoleActive( boolean p_roleActive ) {
		this.g_roleActive = p_roleActive;
	}

	/**
	 * @return the roleCreatedDate
	 */
	public Date getRoleCreatedDate() {
		return this.g_roleCreatedDate;
	}

	/**
	 * @param p_roleCreatedDate
	 *            the roleCreatedDate to set
	 */
	public void setRoleCreatedDate( Date p_roleCreatedDate ) {
		this.g_roleCreatedDate = p_roleCreatedDate;
	}

	/**
	 * @return the roleCreatedBy
	 */
	public String getRoleCreatedBy() {
		return this.g_roleCreatedBy;
	}

	/**
	 * @param p_roleCreatedBy
	 *            the roleCreatedBy to set
	 */
	public void setRoleCreatedBy( String p_roleCreatedBy ) {
		this.g_roleCreatedBy = p_roleCreatedBy;
	}

	/**
	 * @return the roleUdpateDate
	 */
	public Date getRoleUdpateDate() {
		return this.g_roleUdpateDate;
	}

	/**
	 * @param p_roleUdpateDate
	 *            the roleUdpateDate to set
	 */
	public void setRoleUdpateDate( Date p_roleUdpateDate ) {
		this.g_roleUdpateDate = p_roleUdpateDate;
	}

	/**
	 * @return the roleUpdateBy
	 */
	public String getRoleUpdateBy() {
		return this.g_roleUpdateBy;
	}

	/**
	 * @param p_roleUpdateBy
	 *            the roleUpdateBy to set
	 */
	public void setRoleUpdateBy( String p_roleUpdateBy ) {
		this.g_roleUpdateBy = p_roleUpdateBy;
	}

	/**
	 * @return the roleUsers
	 */
	public List<UserRole> getRoleUsers() {
		return this.g_roleUsers;
	}

	/**
	 * @param p_roleUsers
	 *            the roleUsers to set
	 */
	public void setRoleUsers( List<UserRole> p_roleUsers ) {
		this.g_roleUsers = p_roleUsers;
	}
/*
	*//**
	 * @return the roleApplication
	 *//*
	public List<ApplicationRole> getRoleApplication() {
		return this.g_roleApplication;
	}

	*//**
	 * @param p_roleApplication
	 *            the roleApplication to set
	 *//*
	public void setRoleApplication( List<ApplicationRole> p_roleApplication ) {
		this.g_roleApplication = p_roleApplication;
	}
*/
	
	/**
	 * @return the roleAppnId
	 */
	public Application getRoleAppnId() {
		return this.g_roleAppnId;
	}

	/**
	 * @param p_roleAppnId the roleAppnId to set
	 */
	public void setRoleAppnId( Application p_roleAppnId ) {
		this.g_roleAppnId = p_roleAppnId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_roleId == null) ? 0 : this.g_roleId.hashCode());
		result = prime * result + ((this.g_roleName == null) ? 0 : this.g_roleName.hashCode());
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
		if (!(obj instanceof Rol))
			return false;
		Rol other = (Rol) obj;
		if (this.g_roleId == null) {
			if (other.g_roleId != null)
				return false;
		} else if (!this.g_roleId.equals(other.g_roleId))
			return false;
		if (this.g_roleName == null) {
			if (other.g_roleName != null)
				return false;
		} else if (!this.g_roleName.equals(other.g_roleName))
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
		return "Rol [g_roleId=" + this.g_roleId + ", g_roleName=" + this.g_roleName + ", g_roleDescription="
				+ this.g_roleDescription + ", g_roleActive=" + this.g_roleActive + ", g_roleCreatedDate="
				+ this.g_roleCreatedDate + ", g_roleCreatedBy=" + this.g_roleCreatedBy + ", g_roleUdpateDate="
				+ this.g_roleUdpateDate + ", g_roleUpdateBy=" + this.g_roleUpdateBy + "]";
	}

	
}
