package mx.com.lctpc.helpdeck.pojo;

public class SelectList {
	String g_value;
	String g_label;
	
	
	public SelectList( String p_value, String p_label ) {
		this.g_value = p_value;
		this.g_label = p_label;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return this.g_value;
	}
	/**
	 * @param p_value the value to set
	 */
	public void setValue( String p_value ) {
		this.g_value = p_value;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return this.g_label;
	}
	/**
	 * @param p_label the label to set
	 */
	public void setLabel( String p_label ) {
		this.g_label = p_label;
	}
	
	
}
