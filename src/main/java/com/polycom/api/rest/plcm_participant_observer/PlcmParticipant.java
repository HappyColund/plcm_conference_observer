package com.polycom.api.rest.plcm_participant_observer;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import com.polycom.api.rest.plcm_conference_observer.PlcmObject;

@Root(name = "plcm-participant", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-participant")
@Order(elements = { "participant-identifier", "display-name", "endpoint-identifier", "endpoint-name",
      "dial-string", "conference-identifier", "mcu-name", "chairperson", "lecturer", "audio-mute",
      "video-mute", "passback", "passthru" })
public class PlcmParticipant implements PlcmObject
{
   private static final long serialVersionUID = 821443931460866396L;

   public PlcmParticipant()
   {
   }

   public PlcmParticipant(String displayNameValue, String dialStringValue)
   {
      this.displayName = displayNameValue;
      this.dialString = dialStringValue;
   }

   public PlcmParticipant(String displayNameValue, String dialStringValue, boolean audioMuteValue,
         boolean videoMuteValue)
   {
      this.displayName = displayNameValue;
      this.dialString = dialStringValue;
      this.audioMute = audioMuteValue;
      this.videoMute = videoMuteValue;
   }

   @Element(name = "participant-identifier", required = false)
   private String participantIdentifier;

   @Element(name = "display-name", required = false)
   private String displayName;

   @Element(name = "endpoint-identifier", required = false)
   private String endpointIdentifier;

   @Element(name = "endpoint-name", required = false)
   private String endpointName;

   @Element(name = "dial-string")
   private String dialString;

   @Element(name = "conference-identifier", required = false)
   private String conferenceIdentifier;

   @Element(name = "mcu-name", required = false)
   private String mcuName;

   @Element(name = "chairperson", required = false)
   private boolean chairperson;

   @Element(name = "lecturer", required = false)
   private boolean lecturer;

   @Element(name = "audio-mute", required = false)
   private boolean audioMute;

   @Element(name = "video-mute", required = false)
   private boolean videoMute;

   @Element(name = "passback", required = false)
   private String passback;

   @Element(name = "passthru", required = false)
   private String passthru;

   public String getParticipantIdentifier()
   {
      return participantIdentifier;
   }

   public void setParticipantIdentifier(String value)
   {
      this.participantIdentifier = value;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public void setDisplayName(String value)
   {
      this.displayName = value;
   }

   public String getEndpointIdentifier()
   {
      return endpointIdentifier;
   }

   public void setEndpointIdentifier(String value)
   {
      this.endpointIdentifier = value;
   }

   public String getEndpointName()
   {
      return endpointName;
   }

   public void setEndpointName(String value)
   {
      this.endpointName = value;
   }

   public String getDialString()
   {
      return dialString;
   }

   public void setDialString(String value)
   {
      this.dialString = value;
   }

   public String getConferenceIdentifier()
   {
      return conferenceIdentifier;
   }

   public void setConferenceIdentifier(String value)
   {
      this.conferenceIdentifier = value;
   }

   public boolean isChairperson()
   {
      return chairperson;
   }

   public void setChairperson(boolean value)
   {
      this.chairperson = value;
   }

   public boolean isLecturer()
   {
      return lecturer;
   }

   public void setLecturer(boolean value)
   {
      this.lecturer = value;
   }

   public boolean isAudioMute()
   {
      return audioMute;
   }

   public void setAudioMute(boolean value)
   {
      this.audioMute = value;
   }

   public boolean isVideoMute()
   {
      return videoMute;
   }

   public void setVideoMute(boolean value)
   {
      this.videoMute = value;
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

   public String getMcuName()
   {
      return this.mcuName;
   }

   public void setMcuName(String value)
   {
      this.mcuName = value;
   }

   @Override
   public String toString()
   {
      return "PlcmParticipant [participantIdentifier=" + participantIdentifier + ", displayName="
            + displayName + ", endpointIdentifier=" + endpointIdentifier + ", endpointName=" + endpointName
            + ", dialString=" + dialString + ", conferenceIdentifier=" + conferenceIdentifier + ", mcuName="
            + mcuName + ", chairperson=" + chairperson + ", lecturer=" + lecturer + ", audioMute="
            + audioMute + ", videoMute=" + videoMute + ", passback=" + passback + ", passthru=" + passthru
            + "]";
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((participantIdentifier == null) ? 0 : participantIdentifier.hashCode());
      return result;
   }

   /**
    * When adding a participant, the only unique identifier is the participant identifier
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }

      PlcmParticipant other = (PlcmParticipant) obj;

      if (participantIdentifier == null)
      {
         if (other.participantIdentifier != null)
            return false;
      }
      else if (!participantIdentifier.equals(other.participantIdentifier))
      {
         return false;
      }

      return true;
   }
}
