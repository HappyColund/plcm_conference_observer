package com.polycom.api.rest.plcm_participant_observer;

import com.polycom.api.rest.common.SignalingProtocol;

public class PlcmParticipantV2 {

	private String participantIdentifier;
	private String displayName;
	private String endpointIdentifier;
	private String endpointName;
	private String conferenceIdentifier;
	private String mcuName;
	private String connectionStatus;
	private boolean chairperson;
	private boolean lecturer;
	private boolean audioMute;
	private boolean videoMute;
	private SignalingProtocol signalingProtocol;
	private boolean encryptedMedia;
	private boolean speaker;
	
	public boolean isSpeaker() {
		return speaker;
	}
	public void setSpeaker(boolean speaker) {
		this.speaker = speaker;
	}
	public String getParticipantIdentifier() {
		return participantIdentifier;
	}
	public void setParticipantIdentifier(String participantIdentifier) {
		this.participantIdentifier = participantIdentifier;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEndpointIdentifier() {
		return endpointIdentifier;
	}
	public void setEndpointIdentifier(String endpointIdentifier) {
		this.endpointIdentifier = endpointIdentifier;
	}
	public String getEndpointName() {
		return endpointName;
	}
	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}
	public String getConferenceIdentifier() {
		return conferenceIdentifier;
	}
	public void setConferenceIdentifier(String conferenceIdentifier) {
		this.conferenceIdentifier = conferenceIdentifier;
	}
	public String getMcuName() {
		return mcuName;
	}
	public void setMcuName(String mcuName) {
		this.mcuName = mcuName;
	}
	public String getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	public boolean isChairperson() {
		return chairperson;
	}
	public void setChairperson(boolean chairperson) {
		this.chairperson = chairperson;
	}
	public boolean isLecturer() {
		return lecturer;
	}
	public void setLecturer(boolean lecturer) {
		this.lecturer = lecturer;
	}
	public boolean isAudioMute() {
		return audioMute;
	}
	public void setAudioMute(boolean audioMute) {
		this.audioMute = audioMute;
	}
	public boolean isVideoMute() {
		return videoMute;
	}
	public void setVideoMute(boolean videoMute) {
		this.videoMute = videoMute;
	}
	
	public SignalingProtocol getSignalingProtocol() {
		return signalingProtocol;
	}
	public void setSignalingProtocol(SignalingProtocol signalingProtocol) {
		this.signalingProtocol = signalingProtocol;
	}
	public boolean isEncryptedMedia() {
		return encryptedMedia;
	}
	public void setEncryptedMedia(boolean encryptedMedia) {
		this.encryptedMedia = encryptedMedia;
	}
	
}
