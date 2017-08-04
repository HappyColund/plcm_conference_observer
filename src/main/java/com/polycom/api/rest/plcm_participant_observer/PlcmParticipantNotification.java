package com.polycom.api.rest.plcm_participant_observer;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import com.polycom.api.rest.common.NotificationType;
import com.polycom.api.rest.plcm_conference_observer.PlcmNotification;
import com.polycom.api.rest.plcm_conference_observer.PlcmObject;

@Root(name = "plcm-participant-notification", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-participant-notification")
@Order(elements = { "plcm-notification", "plcm-participant", "plcm-participant-list" })
public class PlcmParticipantNotification implements PlcmObject
{
   private static final long serialVersionUID = -3396560175349385914L;

   @Element(name = "plcm-notification")
   private PlcmNotification plcmNotification;

   /**
    * Even though it is mandatory to have either a participant or participant list, the required flag for both
    * members needs to be false so that SimlpeXml doesn't expected both members when it parses the object.
    * Only one member or the other is present.
    */
   @Element(name = "plcm-participant", required = false)
   private PlcmParticipant plcmParticipant;

   @Element(name = "plcm-participant-list", required = false)
   private PlcmParticipantList plcmParticipantList;

   public PlcmNotification getPlcmNotification()
   {
      return plcmNotification;
   }

   public void setPlcmNotification(PlcmNotification value)
   {
      this.plcmNotification = value;
   }

   public NotificationType getNotificationType()
   {
      return this.plcmNotification.getNotificationType();
   }

   public PlcmParticipant getPlcmParticipant()
   {
      return plcmParticipant;
   }

   public void setPlcmParticipant(PlcmParticipant value)
   {
      this.plcmParticipant = value;
   }

   public PlcmParticipantList getPlcmParticipantList()
   {
      return plcmParticipantList;
   }

   public void setPlcmParticipantList(PlcmParticipantList value)
   {
      this.plcmParticipantList = value;
   }

   public List<PlcmParticipant> getParticipants()
   {
      return this.plcmParticipantList.participantList;
   }

   @Override
   public String toString()
   {
      return "PlcmParticipantNotification [plcmNotification=" + plcmNotification + ", plcmParticipant="
            + plcmParticipant + ", plcmParticipantList=" + plcmParticipantList + "]";
   }
}
