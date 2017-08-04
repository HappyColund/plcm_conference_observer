
package com.polycom.api.rest.plcm_conference_observer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Date;

public class PlcmConferenceObserverResource {

	static final String plcmConferenceNotificationMediaType1 = "application/vnd.plcm.plcm-subscription+xml";
	/*static final String plcmConferenceNotificationMediaType2 = "application/vnd.plcm.plcm-conference-notification-v2+xml";
	static final String plcmConferenceNotificationMediaType3 = "application/vnd.plcm.plcm-conference-notification-v3+xml";*/

	@POST
	@Produces(plcmConferenceNotificationMediaType1)
	@Path("/api/rest/notifications/conference")
	public Response receiveConferenceNotification(PlcmConferenceNotification notification) {
		String date = new Date().toString();
		System.out.println(date);
		if (notification.getPlcmNotification() != null) {
			System.out.println("Conference notification received: " + date
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
			System.out.println("Malformed conference notification received. " + date);
		}
		if (notification.getPlcmConference() != null) {
			logConference(notification.getPlcmConference());
		}
		if (notification.getPlcmConferenceList() != null) {
			for (PlcmConference conference : notification.getPlcmConferenceList()) {
				logConference(conference);
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

	private void logConference(PlcmConference conference) {
		System.out.println("Conference:" + "\n\t Conference room identifier: "
				+ conference.getConferenceRoomIdentifier()
				+ "\n\t Conference identifier: "
				+ conference.getConferenceIdentifier()
				+ "\n\t Dial in number: " + conference.getDialInNumber()
				+ "\n\t Host name: " + conference.getHostName()
				+ "\n\t MCU name: " + conference.getMcuName()
				+ "\n\t Owner first name: " + conference.getOwnerFirstName()
				+ "\n\t Owner last name: " + conference.getOwnerLastName()
				+ "\n\t Passback: " + conference.getPassback()
				+ "\n\t Passthru: " + conference.getPassthru());
	}
}
