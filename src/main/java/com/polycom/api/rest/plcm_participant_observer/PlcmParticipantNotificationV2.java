package com.polycom.api.rest.plcm_participant_observer;

import java.util.ArrayList;
import java.util.List;

import com.polycom.api.rest.plcm_conference_observer.PlcmNotification;

public class PlcmParticipantNotificationV2 {

	private PlcmNotification plcmNotification;
	
	private PlcmParticipantV2 plcmParticipantV2;
	
	private List<PlcmParticipantV2> PlcmParticipantListV2 = new ArrayList<PlcmParticipantV2>();

	public PlcmNotification getPlcmNotification() {
		return plcmNotification;
	}

	public void setPlcmNotification(PlcmNotification plcmNotification) {
		this.plcmNotification = plcmNotification;
	}

	public PlcmParticipantV2 getPlcmParticipantV2() {
		return plcmParticipantV2;
	}

	public void setPlcmParticipantV2(PlcmParticipantV2 plcmParticipantV2) {
		this.plcmParticipantV2 = plcmParticipantV2;
	}

	public List<PlcmParticipantV2> getPlcmParticipantListV2() {
		return PlcmParticipantListV2;
	}

	public void setPlcmParticipantListV2(List<PlcmParticipantV2> plcmParticipantListV2) {
		PlcmParticipantListV2 = plcmParticipantListV2;
	}
	
	
}
