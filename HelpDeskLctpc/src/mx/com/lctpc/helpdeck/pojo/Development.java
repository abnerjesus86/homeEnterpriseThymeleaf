package mx.com.lctpc.helpdeck.pojo;

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

@Entity
@Table( name = "DEVELOPMENT", schema = "APPLICATION_MANAGER" )
public class Development {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "DVLP_SEQ" )
	@SequenceGenerator( name = "DVLP_SEQ", sequenceName = "SQ_DVLP_ID", allocationSize = 1 )
	@Column( name = "DVLP_ID" )
	private BigDecimal g_dvlpId;
	@Column( name = "DVLP_NAME" )
	private String g_dvlpName;
	@Column( name = "DVLP_DESCRIPTION" )
	private String g_dvlpDescription;
	@Column( name = "DVLP_ACTIVE" )
	private boolean		g_dvlpActive;
	@CreationTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "DVLP_CREATED_DATE", insertable = true, updatable = false )
	private Timestamp	g_dvlpCreatedDate;
	@Column( name = "DVLP_CREATED_BY", insertable = true, updatable = false )
	private String		g_dvlpCreatedBy;
	@UpdateTimestamp
	@DateTimeFormat( pattern = "dd/MM/yyyy hh:mm" )
	@Column( name = "DVLP_UPDATE_DATE", insertable = true, updatable = true )
	private Timestamp	g_dvlpUdpateDate;
	@Column( name = "DVLP_UPDATE_BY", insertable = true, updatable = true )
	private String		g_dvlpUpdateBy;
	/**
	 * @return the dvlpId
	 */
	public BigDecimal getDvlpId() {
		return this.g_dvlpId;
	}
	/**
	 * @param p_dvlpId the dvlpId to set
	 */
	public void setDvlpId( BigDecimal p_dvlpId ) {
		this.g_dvlpId = p_dvlpId;
	}
	/**
	 * @return the dvlpName
	 */
	public String getDvlpName() {
		return this.g_dvlpName;
	}
	/**
	 * @param p_dvlpName the dvlpName to set
	 */
	public void setDvlpName( String p_dvlpName ) {
		this.g_dvlpName = p_dvlpName;
	}
	/**
	 * @return the dvlpDescription
	 */
	public String getDvlpDescription() {
		return this.g_dvlpDescription;
	}
	/**
	 * @param p_dvlpDescription the dvlpDescription to set
	 */
	public void setDvlpDescription( String p_dvlpDescription ) {
		this.g_dvlpDescription = p_dvlpDescription;
	}
	/**
	 * @return the dvlpActive
	 */
	public boolean isDvlpActive() {
		return this.g_dvlpActive;
	}
	/**
	 * @param p_dvlpActive the dvlpActive to set
	 */
	public void setDvlpActive( boolean p_dvlpActive ) {
		this.g_dvlpActive = p_dvlpActive;
	}
	/**
	 * @return the dvlpCreatedDate
	 */
	public Timestamp getDvlpCreatedDate() {
		return this.g_dvlpCreatedDate;
	}
	/**
	 * @param p_dvlpCreatedDate the dvlpCreatedDate to set
	 */
	public void setDvlpCreatedDate( Timestamp p_dvlpCreatedDate ) {
		this.g_dvlpCreatedDate = p_dvlpCreatedDate;
	}
	/**
	 * @return the dvlpCreatedBy
	 */
	public String getDvlpCreatedBy() {
		return this.g_dvlpCreatedBy;
	}
	/**
	 * @param p_dvlpCreatedBy the dvlpCreatedBy to set
	 */
	public void setDvlpCreatedBy( String p_dvlpCreatedBy ) {
		this.g_dvlpCreatedBy = p_dvlpCreatedBy;
	}
	/**
	 * @return the dvlpUdpateDate
	 */
	public Timestamp getDvlpUdpateDate() {
		return this.g_dvlpUdpateDate;
	}
	/**
	 * @param p_dvlpUdpateDate the dvlpUdpateDate to set
	 */
	public void setDvlpUdpateDate( Timestamp p_dvlpUdpateDate ) {
		this.g_dvlpUdpateDate = p_dvlpUdpateDate;
	}
	/**
	 * @return the dvlpUpdateBy
	 */
	public String getDvlpUpdateBy() {
		return this.g_dvlpUpdateBy;
	}
	/**
	 * @param p_dvlpUpdateBy the dvlpUpdateBy to set
	 */
	public void setDvlpUpdateBy( String p_dvlpUpdateBy ) {
		this.g_dvlpUpdateBy = p_dvlpUpdateBy;
	}
	
	
	
}
