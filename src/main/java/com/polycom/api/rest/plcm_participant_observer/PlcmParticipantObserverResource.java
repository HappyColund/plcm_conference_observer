
package com.polycom.api.rest.plcm_participant_observer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Date;

public class PlcmParticipantObserverResource {

	static final String plcmParticipantNotificationMediaType = "application/vnd.plcm.plcm-participant-notification+xml";
    static final String plcmParticipantV2NotificationMediaType = "application/vnd.plcm.plcm-participant-notification-v2+xml";

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
			for (PlcmParticipant participant : notification.getPlcmParticipantList().getParticipantList()) {
				logParticipant(participant);
			}
		}

		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

    @POST
    @Consumes(plcmParticipantV2NotificationMediaType)
    public Response receiveParticipantV2Notification(
            PlcmParticipantNotificationV2 notificationV2) {
        String date = new Date().toString();
        if (notificationV2.getPlcmNotification() != null) {
            System.out.println("Participant notification V2 received: " + date
                    + "\n\t Resource URL: " + notificationV2.getPlcmNotification().getResourceUrl()
                    + "\n\t Subscription URL: " + notificationV2.getPlcmNotification().getSubscriptionUrl()
                    + "\n\t Notification type: " + notificationV2.getPlcmNotification().getNotificationType()
                    + "\n\t Passback: " + notificationV2.getPlcmNotification().getPassback()
                    + "\n\t Passthru: " + notificationV2.getPlcmNotification().getPassthru());
        } else {
            System.out.println("Malformed participant notification V2 received. " + date);
        }
        if (notificationV2.getPlcmParticipantV2() != null) {
            logParticipantV2(notificationV2.getPlcmParticipantV2());
        }
        if (notificationV2.getPlcmParticipantListV2() != null) {
            for (PlcmParticipantV2 participantV2 : notificationV2.getPlcmParticipantListV2()) {
                logParticipantV2(participantV2);
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
		System.out.println("Participant:" 
		        + "\n\t Participant identifier: " + participant.getParticipantIdentifier()
				+ "\n\t Display name: " + participant.getDisplayName()
/*                + "\n\t Signaling Protocol: " + participant.getSignalingProtocol().value()
*/				+ "\n\t Chairperson: " + participant.isChairperson()
				+ "\n\t Lecturer: " + participant.isLecturer()
				+ "\n\t Audio mute: " + participant.isAudioMute()
				+ "\n\t Video mute: " + participant.isVideoMute()
            + "\n\t MCU name: " + participant.getMcuName());
           /* + "\n\t Speaker: " + participant.isSpeaker());*/
	}

	private void logParticipantV2(PlcmParticipantV2 participantV2) {
       System.out.println("ParticipantV2:" 
	           + "\n\t Participant identifier: " + participantV2.getParticipantIdentifier()
               + "\n\t Display name: " + participantV2.getDisplayName()
               + "\n\t Signaling Protocol: " + participantV2.getSignalingProtocol()
               + "\n\t Chairperson: " + participantV2.isChairperson()
               + "\n\t Lecturer: " + participantV2.isLecturer()
               + "\n\t Audio mute: " + participantV2.isAudioMute()
               + "\n\t Video mute: " + participantV2.isVideoMute()
           + "\n\t MCU name: " + participantV2.getMcuName()
           + "\n\t Speaker: " + participantV2.isSpeaker());
           ;
   }
}
