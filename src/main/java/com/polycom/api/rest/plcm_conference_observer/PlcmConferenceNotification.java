package com.polycom.api.rest.plcm_conference_observer;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "plcm-conference-notification", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-conference-notification")
public class PlcmConferenceNotification implements PlcmObject{
	
	private static final long serialVersionUID = 1370367116233149493L;

	@ElementList(name = "plcm-conference-list",inline=true)
	private List<PlcmConference> plcmConferenceList = new ArrayList<PlcmConference>();
	
	@Element(name = "plcm-conference")
	private PlcmConference plcmConference;
	
	@Element(name = "plcm-notification")
	private PlcmNotification plcmNotification;
	
	
	public List<PlcmConference> getPlcmConferenceList() {
		return plcmConferenceList;
	}
	public void setPlcmConferenceList(List<PlcmConference> plcmConferenceList) {
		this.plcmConferenceList = plcmConferenceList;
	}
	public PlcmConference getPlcmConference() {
		return plcmConference;
	}
	public void setPlcmConference(PlcmConference plcmConference) {
		this.plcmConference = plcmConference;
	}
	public PlcmNotification getPlcmNotification() {
		return plcmNotification;
	}
	public void setPlcmNotification(PlcmNotification plcmNotification) {
		this.plcmNotification = plcmNotification;
	}
	@Override
	public String toString() {
		return "PlcmConferenceNotification [plcmConferenceList=" + plcmConferenceList + ", plcmConference="
				+ plcmConference + ", plcmNotification=" + plcmNotification + "]";
	}
	
}
