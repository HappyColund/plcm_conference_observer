package com.polycom.api.rest.plcm_conference_observer;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "plcm-conference", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-conference")
public class PlcmConference implements PlcmObject
{
   private static final long serialVersionUID = -7585484764609186810L;

   @Element(name = "conference-identifier")
   private String conferenceIdentifier;

   @Element(name = "dial-in-number")
   private String dialInNumber;

   @Element(name = "conference-room-identifier")
   private String conferenceRoomIdentifier;

   @Element(name = "mcu-name")
   private String mcuName;

   @Element(name = "owner-first-name")
   private String ownerFirstName;

   @Element(name = "owner-last-name")
   private String ownerLastName;

   @Element(name = "host-name")
   private String hostName;

   @Element(name = "recording-active", required = false)
   private boolean recordingActive;

   @Element(name = "conference-locked", required = false)
   private boolean conferenceLocked;

   @Element(name = "join-new-calls-muted", required = false)
   private boolean joinNewCallsMuted;

   @Element(name = "passback", required = false)
   private String passback;

   @Element(name = "passthru", required = false)
   private String passthru;

   @Element(name = "entity-tag", required = false)
   private String entityTag;

   public String getConferenceIdentifier()
   {
      return conferenceIdentifier;
   }

   public void setConferenceIdentifier(String value)
   {
      this.conferenceIdentifier = value;
   }

   public String getDialInNumber()
   {
      return dialInNumber;
   }

   public void setDialInNumber(String value)
   {
      this.dialInNumber = value;
   }

   public String getConferenceRoomIdentifier()
   {
      return conferenceRoomIdentifier;
   }

   public void setConferenceRoomIdentifier(String value)
   {
      this.conferenceRoomIdentifier = value;
   }

   public String getMcuName()
   {
      return mcuName;
   }

   public void setMcuName(String value)
   {
      this.mcuName = value;
   }

   public String getOwnerFirstName()
   {
      return ownerFirstName;
   }

   public void setOwnerFirstName(String value)
   {
      this.ownerFirstName = value;
   }

   public String getOwnerLastName()
   {
      return ownerLastName;
   }

   public void setOwnerLastName(String value)
   {
      this.ownerLastName = value;
   }

   public String getHostName()
   {
      return hostName;
   }

   public void setHostName(String value)
   {
      this.hostName = value;
   }

   public boolean isRecordingActive()
   {
      return recordingActive;
   }

   public void setRecordingActive(boolean value)
   {
      this.recordingActive = value;
   }

   public boolean isConferenceLocked()
   {
      return conferenceLocked;
   }

   public void setConferenceLocked(boolean value)
   {
      this.conferenceLocked = value;
   }

   public boolean isJoinNewCallsMuted()
   {
      return joinNewCallsMuted;
   }

   public void setJoinNewCallsMuted(boolean value)
   {
      this.joinNewCallsMuted = value;
   }

   public String getPassback()
   {
      return passback;
   }

   public void setPassback(String value)
   {
      this.passback = value;
   }

   public String getPassthru()
   {
      return passthru;
   }

   public void setPassthru(String value)
   {
      this.passthru = value;
   }

   public String getEntityTag()
   {
      return entityTag;
   }

   public void setEntityTag(String value)
   {
      this.entityTag = value;
   }

   @Override
   public String toString()
   {
      return "PlcmConference [conferenceIdentifier=" + conferenceIdentifier + ", dialInNumber="
            + dialInNumber + ", conferenceRoomIdentifier=" + conferenceRoomIdentifier + ", mcuName="
            + mcuName + ", ownerFirstName=" + ownerFirstName + ", ownerLastName=" + ownerLastName
            + ", hostName=" + hostName + ", recordingActive=" + recordingActive + ", conferenceLocked="
            + isConferenceLocked() + ", join-new-calls-muted=" + isJoinNewCallsMuted() + ", passback="
            + passback + ", passthru=" + passthru + ", entityTag=" + entityTag + "]";
   }
}
