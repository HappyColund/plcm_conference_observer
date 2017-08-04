package com.polycom.api.rest.plcm_conference_observer;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.polycom.api.rest.common.NotificationType;

@Root(name = "plcm-notification", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-notification")
public class PlcmNotification implements PlcmObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9136719696980065731L;
	
	@Element(name = "notification-type")
	private NotificationType notificationType;
	
	@Element(name = "subscription-url")
	private String subscriptionUrl;
	
	@Element(name = "resource-url")
	private String resourceUrl;
	
	@Element(name = "sequence-number")
	private Long sequenceNumber;
	
	@Element(name = "passback")
	private String passback;
	
	@Element(name = "passthru")
	private String passthru;
	
	
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}
	public void setSubscriptionUrl(String subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getPassback() {
		return passback;
	}
	public void setPassback(String passback) {
		this.passback = passback;
	}
	public String getPassthru() {
		return passthru;
	}
	public void setPassthru(String passthru) {
		this.passthru = passthru;
	}
	@Override
	public String toString() {
		return "PlcmNotification [notificationType=" + notificationType + ", subscriptionUrl=" + subscriptionUrl
				+ ", resourceUrl=" + resourceUrl + ", sequenceNumber=" + sequenceNumber + ", passback=" + passback
				+ ", passthru=" + passthru + "]";
	}
	
}
