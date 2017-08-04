package com.polycom.api.rest.both;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;

import com.polycom.api.rest.common.InsecureHttpClientFactory;
import com.polycom.api.rest.plcm_conference_observer.PlcmConference;
import com.polycom.api.rest.plcm_conference_observer.PlcmConferenceNotification;

public class PlcmConferenceObserverResource {

	static final String CONFERENCE_ROOT = "/api/rest/conferences";

	static final String plcmConferenceNotificationMediaType = "application/vnd.plcm.plcm-conference-notification+xml";
	static final String plcmSubscriptionMediaType = "application/vnd.plcm.plcm-subscription+xml";

	private String partConsumerUrl;
	private static InsecureHttpClientFactory clientFactory = null;

	private static Map<String, String> confIdToSubscriptionIdMap = new HashMap<String, String>();

	public PlcmConferenceObserverResource() {
	}

	public PlcmConferenceObserverResource(String partConsumerUrl) {
		this.partConsumerUrl = partConsumerUrl;
	}

	@POST
	@Consumes(plcmConferenceNotificationMediaType)
	public Response receiveConferenceNotification(PlcmConferenceNotification notification) {
		String date = new Date().toString();
		if (notification.getPlcmNotification() != null) {
			System.out.println("Conference notification received: " + date + "\n\t Resource URL: "
					+ notification.getPlcmNotification().getResourceUrl() + "\n\t Subscription URL: "
					+ notification.getPlcmNotification().getSubscriptionUrl() + "\n\t Notification type: "
					+ notification.getPlcmNotification().getNotificationType() + "\n\t Passback: "
					+ notification.getPlcmNotification().getPassback() + "\n\t Passthru: "
					+ notification.getPlcmNotification().getPassthru());
		} else {
			System.out.println("Malformed conference notification received. " + date);
		}
		if (notification.getPlcmConference() != null) {
			logConference(notification.getPlcmConference());
			processSubscription(notification.getPlcmConference(), notification);
		}
		if (notification.getPlcmConferenceList() != null) {
			for (PlcmConference conference : notification.getPlcmConferenceList()) {
				logConference(conference);
				processSubscription(conference, notification);
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

	private void processSubscription(PlcmConference conference, PlcmConferenceNotification notification) {
		String confUuid = conference.getConferenceIdentifier();
		switch (notification.getPlcmNotification().getNotificationType()) {
		case ADD:
			System.out.println("Subscribing to participants for '" + confUuid + "'");
			String url = notification.getPlcmNotification().getResourceUrl() + "/" + confUuid + "/participants";
			String subscribeXML = "<plcm-subscription xmlns=\"urn:com:polycom:api:rest:plcm-subscription\"><consumer-url>"
					+ partConsumerUrl + "</consumer-url><time-to-live>93600</time-to-live></plcm-subscription>";
			try {
				HttpClient httpClient = getClientFactory().buildHttpClient();
				HttpPost httpPost = new HttpPost(url);

				StringEntity entity = new StringEntity(subscribeXML, "UTF-8");
				entity.setContentType(plcmSubscriptionMediaType);
				httpPost.setEntity(entity);

				UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
				BasicScheme scheme = new BasicScheme();
				@SuppressWarnings("deprecation")
				Header authenticationHeader = scheme.authenticate(credentials, httpPost);
				httpPost.addHeader(authenticationHeader);

				HttpResponse httpResponse = httpClient.execute(httpPost);
				Header[] allHeaders = httpResponse.getAllHeaders();
				for (Header header : allHeaders) {
					if (header.getName().equals("Location")) {
						System.out.println("Participant list for conference '" + confUuid + "' has subscribe id: "
								+ header.getValue());
						confIdToSubscriptionIdMap.put(confUuid, header.getValue());
					}
				}
			} catch (Exception e) {
				System.out.println("Error subscribing to participant notifications for conference '" + confUuid + "'");
				e.printStackTrace();
			}

			break;

		case DELETE:
			System.out.println("Unsubscribing participants for '" + confUuid + "'");

			try {
				HttpClient httpClient = getClientFactory().buildHttpClient();
				HttpDelete httpDelete = new HttpDelete(confIdToSubscriptionIdMap.get(confUuid));

				UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
				BasicScheme scheme = new BasicScheme();
				@SuppressWarnings("deprecation")
				Header authenticationHeader = scheme.authenticate(credentials, httpDelete);
				httpDelete.addHeader(authenticationHeader);

				httpClient.execute(httpDelete);
				confIdToSubscriptionIdMap.remove(confUuid);
			} catch (Exception e) {
				System.out.println("Error subscribing to participant notifications for conference '" + confUuid + "'");
				e.printStackTrace();
			}

		default:
			break;
		}
	}

	private static InsecureHttpClientFactory getClientFactory() {
		if (clientFactory == null) {
			clientFactory = new InsecureHttpClientFactory();
		}

		return clientFactory;
	}

	private void logConference(PlcmConference conference) {
		System.out.println("Conference:" + "\n\t Conference room identifier: "
				+ conference.getConferenceRoomIdentifier() + "\n\t Conference identifier: "
				+ conference.getConferenceIdentifier() + "\n\t Dial in number: " + conference.getDialInNumber()
				+ "\n\t Host name: " + conference.getHostName() + "\n\t MCU name: " + conference.getMcuName()
				+ "\n\t Owner first name: " + conference.getOwnerFirstName() + "\n\t Owner last name: "
				+ conference.getOwnerLastName() + "\n\t Passback: " + conference.getPassback() + "\n\t Passthru: "
				+ conference.getPassthru());
	}
}
