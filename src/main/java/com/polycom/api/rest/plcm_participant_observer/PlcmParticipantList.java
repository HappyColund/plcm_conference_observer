package com.polycom.api.rest.plcm_participant_observer;


import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.polycom.api.rest.plcm_conference_observer.PlcmObject;

@Root(name = "plcm-participant-list", strict = false)
@Namespace(reference = "urn:com:polycom:api:rest:plcm-participant")
public class PlcmParticipantList implements PlcmObject
{
   private static final long serialVersionUID = 4174816264566548590L;
   
   @ElementList(inline = true)
   List<PlcmParticipant> participantList;

   public List<PlcmParticipant> getParticipantList()
   {
      return participantList;
   }

   public void setParticipantList(List<PlcmParticipant> value)
   {
      this.participantList = value;
   }

   @Override
   public String toString()
   {
      return "PlcmParticipantList [participantList=" + participantList + "]";
   }
}
