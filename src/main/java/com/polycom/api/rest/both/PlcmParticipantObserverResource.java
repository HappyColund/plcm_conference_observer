package com.polycom.api.rest.both;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.polycom.api.rest.plcm_participant_observer.PlcmParticipant;
import com.polycom.api.rest.plcm_participant_observer.PlcmParticipantNotification;

import java.util.Date;

public class PlcmParticipantObserverResource {

	static final String plcmParticipantNotificationMediaType = "application/vnd.plcm.plcm-participant-notification+xml";

	@POST
	@Consumes(plcmParticipantNotificationMediaType)
	public Response receiveParticipantNotification(
			PlcmParticipantNotification notification) {
		String date = new Date().toString();
		if (notification.getPlcmNotification() != null) {
			System.out.println("Participant notification received: " + date
					+ "\n\t Resource URL: "
					+ notification.getPlcmNotification().getResourceUrl()
					+ "\n\t Subscription URL: "
					+ notification.getPlcmNotification().getSubscriptionUrl()
					+ "\n\t Notification type: "
					+ notification.getPlcmNotification().getNotificationType()
					+ "\n\t Passback: "
					+ notification.getPlcmNotification().getPassback()
					+ "\n\t Passthru: "
					+ notification.getPlcmNotification().getPassthru());
		} else {
			System.out.println("Malformed participant notification received. " + date);
		}
		if (notification.getPlcmParticipant() != null) {
			logParticipant(notification.getPlcmParticipant());
		}
		if (notification.getPlcmParticipantList() != null) {
			for (PlcmParticipant participant : notification
					.getPlcmParticipantList().getParticipantList()) {
				logParticipant(participant);
			}
		}

		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@GET
	public Response handleValidatorGetRequest() {
		System.out.println("Consumer url validator GET request received.");
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	private void logParticipant(PlcmParticipant participant) {
		System.out.println("Participant:" + "\n\t Participant identifier: "
				+ participant.getParticipantIdentifier()
				+ "\n\t Display name: " + participant.getDisplayName()
				+ "\n\t Chairperson: " + participant.isChairperson()
				+ "\n\t Lecturer: " + participant.isLecturer()
				+ "\n\t Audio mute: " + participant.isAudioMute()
				+ "\n\t Video mute: " + participant.isVideoMute()
            + "\n\t MCU name: " + participant.getMcuName());
           /* + "\n\t Speaker: " + participant.isSpeaker());*/
	}
}
