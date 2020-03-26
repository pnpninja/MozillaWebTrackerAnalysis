package edu.stonybrook.pragsec.webtracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.SequenceGenerator;

@Entity(name = "WebsiteDetail")
public class WebsiteDetail {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String URL;
	
	@Column(nullable = true)
	private boolean screenshot_taken = false;
	
	@Column(nullable = true)
	private boolean logs_fetched = false;
	
	@Column(nullable = true)
	private String tracking_enabled_screenshot_location = null;
	
	@Column(nullable = true)
	private String tracking_disabled_screenshot_location = null;
	
	@Column(nullable = true)
	private String logs_location = null;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public boolean isScreenshot_taken() {
		return screenshot_taken;
	}

	public void setScreenshot_taken(boolean screenshot_taken) {
		this.screenshot_taken = screenshot_taken;
	}

	public boolean isLogs_fetched() {
		return logs_fetched;
	}

	public void setLogs_fetched(boolean logs_fetched) {
		this.logs_fetched = logs_fetched;
	}

	public String getTracking_enabled_screenshot_location() {
		return tracking_enabled_screenshot_location;
	}

	public void setTracking_enabled_screenshot_location(String tracking_enabled_screenshot_location) {
		this.tracking_enabled_screenshot_location = tracking_enabled_screenshot_location;
	}

	public String getTracking_disabled_screenshot_location() {
		return tracking_disabled_screenshot_location;
	}

	public void setTracking_disabled_screenshot_location(String tracking_disabled_screenshot_location) {
		this.tracking_disabled_screenshot_location = tracking_disabled_screenshot_location;
	}

	public String getLogs_location() {
		return logs_location;
	}

	public void setLogs_location(String logs_location) {
		this.logs_location = logs_location;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
