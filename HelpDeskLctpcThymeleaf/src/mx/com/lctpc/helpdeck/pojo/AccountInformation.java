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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table( name = "ACCOUNT_INFORMATION", schema = "APPLICATION_MANAGER" )
/*@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "acinUserId")*/
public class AccountInformation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * ACIN_USER_ID NOT NULL NUMBER ACIN_PROFILE_PICTURE NOT NULL VARCHAR2(150)
	 * ACIN_TELEPHONE NUMBER(10) ACIN_LAST_ACCESS NOT NULL DATE
	 * ACIN_ALTERNATE_EMAIL VARCHAR2(150) ACIN_CREATED_DATE NOT NULL DATE
	 * ACIN_CREATED_BY NOT NULL VARCHAR2(150) ACIN_UPDATE_DATE DATE
	 * ACIN_UPDATE_BY VARCHAR2(150)
	 */
	
	@Id
	@Column( name = "ACIN_USER_ID" )
	/*@GeneratedValue( generator = "myGeneratorUserId" )
	@GenericGenerator( name = "myGeneratorUserId", strategy = "foreign", parameters = @Parameter( value = "g_user", name = "property" ) )
	*/private BigDecimal	g_acinUserId;
	@Column( name = "ACIN_PROFILE_PICTURE" )
	private String		g_acinProfilePicture;
	@Column( name = "ACIN_NAME" )
	private String	g_acinName;
	@Column( name = "ACIN_LAST_NAME" )
	private String	g_acinLastName;
	@Column( name = "ACIN_EMAIL" )
	private String	g_acinEmail;
	@Column( name = "ACIN_ALTERNATE_EMAIL" )
	private String		g_acinAlternateEmail;
	@Column( name = "ACIN_GENDER" )
	private String g_acinGender;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ACIN_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_acinCreatedDate;
	@Column( name = "ACIN_CREATED_BY", insertable = true, updatable = false )
	private String		g_acinCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "ACIN_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_acinUpdateDate;
	@Column( name = "ACIN_UPDATE_BY", insertable = true, updatable = true )
	private String		g_acinUpdateBy;
//	@OneToOne
//	@PrimaryKeyJoinColumn
	/*@OneToOne( mappedBy = "g_accountInf", fetch =  FetchType.LAZY)
	private User		g_user;*/

	/**
	 * @return the acinUserId
	 */
	public BigDecimal getAcinUserId() {
		return this.g_acinUserId;
	}

	/**
	 * @param p_acinUserId
	 *            the acinUserId to set
	 */
	public void setAcinUserId( BigDecimal p_acinUserId ) {
		this.g_acinUserId = p_acinUserId;
	}

	/**
	 * @return the acinProfilePicture
	 */
	public String getAcinProfilePicture() {
		return this.g_acinProfilePicture;
	}

	/**
	 * @param p_acinProfilePicture
	 *            the acinProfilePicture to set
	 */
	public void setAcinProfilePicture( String p_acinProfilePicture ) {
		this.g_acinProfilePicture = p_acinProfilePicture;
	}

	/**
	 * @return the acinAlternateEmail
	 */
	public String getAcinAlternateEmail() {
		return this.g_acinAlternateEmail;
	}

	/**
	 * @param p_acinAlternateEmail
	 *            the acinAlternateEmail to set
	 */
	public void setAcinAlternateEmail( String p_acinAlternateEmail ) {
		this.g_acinAlternateEmail = p_acinAlternateEmail;
	}

	/**
	 * @return the acinCreatedDate
	 */
	public Timestamp getAcinCreatedDate() {
		return this.g_acinCreatedDate;
	}

	/**
	 * @param p_acinCreatedDate
	 *            the acinCreatedDate to set
	 */
	public void setAcinCreatedDate( Timestamp p_acinCreatedDate ) {
		this.g_acinCreatedDate = p_acinCreatedDate;
	}

	/**
	 * @return the acinCreatedBy
	 */
	public String getAcinCreatedBy() {
		return this.g_acinCreatedBy;
	}

	/**
	 * @param p_acinCreatedBy
	 *            the acinCreatedBy to set
	 */
	public void setAcinCreatedBy( String p_acinCreatedBy ) {
		this.g_acinCreatedBy = p_acinCreatedBy;
	}

	/**
	 * @return the acinUpdateDate
	 */
	public Timestamp getAcinUpdateDate() {
		return this.g_acinUpdateDate;
	}

	/**
	 * @param p_acinUpdateDate
	 *            the acinUpdateDate to set
	 */
	public void setAcinUpdateDate( Timestamp p_acinUpdateDate ) {
		this.g_acinUpdateDate = p_acinUpdateDate;
	}

	/**
	 * @return the acinUpdateBy
	 */
	public String getAcinUpdateBy() {
		return this.g_acinUpdateBy;
	}

	/**
	 * @param p_acinUpdateBy
	 *            the acinUpdateBy to set
	 */
	public void setAcinUpdateBy( String p_acinUpdateBy ) {
		this.g_acinUpdateBy = p_acinUpdateBy;
	}

	/**
	 * @return the user
	 *//*
	@JsonIgnore
	public User getUser() {
		return this.g_user;
	}

	*//**
	 * @param p_user
	 *            the user to set
	 *//*
	@JsonIgnore
	public void setUser( User p_user ) {
		this.g_user = p_user;
	}
*/
	/**
	 * @return the acinName
	 */
	public String getAcinName() {
		return this.g_acinName;
	}

	/**
	 * @param p_acinName the acinName to set
	 */
	public void setAcinName( String p_acinName ) {
		this.g_acinName = p_acinName;
	}

	/**
	 * @return the acinLastName
	 */
	public String getAcinLastName() {
		return this.g_acinLastName;
	}

	/**
	 * @param p_acinLastName the acinLastName to set
	 */
	public void setAcinLastName( String p_acinLastName ) {
		this.g_acinLastName = p_acinLastName;
	}

	/**
	 * @return the acinEmail
	 */
	public String getAcinEmail() {
		return this.g_acinEmail;
	}

	/**
	 * @param p_acinEmail the acinEmail to set
	 */
	public void setAcinEmail( String p_acinEmail ) {
		this.g_acinEmail = p_acinEmail;
	}

	/**
	 * @return the acinGender
	 */
	public String getAcinGender() {
		return this.g_acinGender;
	}

	/**
	 * @param p_acinGender the acinGender to set
	 */
	public void setAcinGender( String p_acinGender ) {
		this.g_acinGender = p_acinGender;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.g_acinAlternateEmail == null) ? 0 : this.g_acinAlternateEmail.hashCode());
		result = prime * result + ((this.g_acinCreatedBy == null) ? 0 : this.g_acinCreatedBy.hashCode());
		result = prime * result + ((this.g_acinCreatedDate == null) ? 0 : this.g_acinCreatedDate.hashCode());
		result = prime * result + ((this.g_acinEmail == null) ? 0 : this.g_acinEmail.hashCode());
		result = prime * result + ((this.g_acinGender == null) ? 0 : this.g_acinGender.hashCode());
		result = prime * result + ((this.g_acinLastName == null) ? 0 : this.g_acinLastName.hashCode());
		result = prime * result + ((this.g_acinName == null) ? 0 : this.g_acinName.hashCode());
		result = prime * result + ((this.g_acinProfilePicture == null) ? 0 : this.g_acinProfilePicture.hashCode());
		result = prime * result + ((this.g_acinUpdateBy == null) ? 0 : this.g_acinUpdateBy.hashCode());
		result = prime * result + ((this.g_acinUpdateDate == null) ? 0 : this.g_acinUpdateDate.hashCode());
		result = prime * result + ((this.g_acinUserId == null) ? 0 : this.g_acinUserId.hashCode());
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
		if (!(obj instanceof AccountInformation))
			return false;
		AccountInformation other = (AccountInformation) obj;
		if (this.g_acinAlternateEmail == null) {
			if (other.g_acinAlternateEmail != null)
				return false;
		} else if (!this.g_acinAlternateEmail.equals(other.g_acinAlternateEmail))
			return false;
		if (this.g_acinCreatedBy == null) {
			if (other.g_acinCreatedBy != null)
				return false;
		} else if (!this.g_acinCreatedBy.equals(other.g_acinCreatedBy))
			return false;
		if (this.g_acinCreatedDate == null) {
			if (other.g_acinCreatedDate != null)
				return false;
		} else if (!this.g_acinCreatedDate.equals(other.g_acinCreatedDate))
			return false;
		if (this.g_acinEmail == null) {
			if (other.g_acinEmail != null)
				return false;
		} else if (!this.g_acinEmail.equals(other.g_acinEmail))
			return false;
		if (this.g_acinGender == null) {
			if (other.g_acinGender != null)
				return false;
		} else if (!this.g_acinGender.equals(other.g_acinGender))
			return false;
		if (this.g_acinLastName == null) {
			if (other.g_acinLastName != null)
				return false;
		} else if (!this.g_acinLastName.equals(other.g_acinLastName))
			return false;
		if (this.g_acinName == null) {
			if (other.g_acinName != null)
				return false;
		} else if (!this.g_acinName.equals(other.g_acinName))
			return false;
		if (this.g_acinProfilePicture == null) {
			if (other.g_acinProfilePicture != null)
				return false;
		} else if (!this.g_acinProfilePicture.equals(other.g_acinProfilePicture))
			return false;
		if (this.g_acinUpdateBy == null) {
			if (other.g_acinUpdateBy != null)
				return false;
		} else if (!this.g_acinUpdateBy.equals(other.g_acinUpdateBy))
			return false;
		if (this.g_acinUpdateDate == null) {
			if (other.g_acinUpdateDate != null)
				return false;
		} else if (!this.g_acinUpdateDate.equals(other.g_acinUpdateDate))
			return false;
		if (this.g_acinUserId == null) {
			if (other.g_acinUserId != null)
				return false;
		} else if (!this.g_acinUserId.equals(other.g_acinUserId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountInformation [g_acinUserId=" + this.g_acinUserId + ", g_acinProfilePicture=" + this.g_acinProfilePicture + ", g_acinName=" + this.g_acinName + ", g_acinLastName=" + this.g_acinLastName
				+ ", g_acinEmail=" + this.g_acinEmail + ", g_acinAlternateEmail=" + this.g_acinAlternateEmail + ", g_acinGender=" + this.g_acinGender + ", g_acinCreatedDate=" + this.g_acinCreatedDate
				+ ", g_acinCreatedBy=" + this.g_acinCreatedBy + ", g_acinUpdateDate=" + this.g_acinUpdateDate + ", g_acinUpdateBy=" + this.g_acinUpdateBy + "]";
	}

	

}
