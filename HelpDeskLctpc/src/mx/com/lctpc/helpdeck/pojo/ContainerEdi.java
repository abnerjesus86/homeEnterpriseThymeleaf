package mx.com.lctpc.helpdeck.pojo;

import java.util.Date;
import java.util.List;

public class ContainerEdi {
	
	private Date g_InitialDate;
	private Date g_FinalDate;
	private String g_Event;
	private String g_Client;
	private String g_Directory;
	private String g_ReferenceVessel;
	private List<String> g_Containers;

	/**
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return this.g_InitialDate;
	}
	/**
	 * @param p_initialDate the initialDate to set
	 */
	public void setInitialDate( Date p_initialDate ) {
		this.g_InitialDate = p_initialDate;
	}
	/**
	 * @return the finalDate
	 */
	public Date getFinalDate() {
		return this.g_FinalDate;
	}
	/**
	 * @param p_finalDate the finalDate to set
	 */
	public void setFinalDate( Date p_finalDate ) {
		this.g_FinalDate = p_finalDate;
	}
	/**
	 * @return the event
	 */
	public String getEvent() {
		return this.g_Event;
	}
	/**
	 * @param p_event the event to set
	 */
	public void setEvent( String p_event ) {
		this.g_Event = p_event;
	}
	/**
	 * @return the client
	 */
	public String getClient() {
		return this.g_Client;
	}
	/**
	 * @param p_client the client to set
	 */
	public void setClient( String p_client ) {
		this.g_Client = p_client;
	}
	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return this.g_Directory;
	}
	/**
	 * @param p_directory the directory to set
	 */
	public void setDirectory( String p_directory ) {
		this.g_Directory = p_directory;
	}
	/**
	 * @return the referenceVessel
	 */
	public String getReferenceVessel() {
		return this.g_ReferenceVessel;
	}
	/**
	 * @param p_referenceVessel the referenceVessel to set
	 */
	public void setReferenceVessel( String p_referenceVessel ) {
		this.g_ReferenceVessel = p_referenceVessel;
	}
	/**
	 * @return the containers
	 */
	public List<String> getContainers() {
		return this.g_Containers;
	}
	/**
	 * @param p_containers the containers to set
	 */
	public void setContainers( List<String> p_containers ) {
		this.g_Containers = p_containers;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContainerEdi [g_InitialDate=" + this.g_InitialDate + ", g_FinalDate=" + this.g_FinalDate + ", g_Event="
				+ this.g_Event + ", g_Client=" + this.g_Client + ", g_Directory=" + this.g_Directory
				+ ", g_ReferenceVessel=" + this.g_ReferenceVessel + ", g_Containers=" + this.g_Containers + "]";
	}
	
	
	
	
	
	
}
