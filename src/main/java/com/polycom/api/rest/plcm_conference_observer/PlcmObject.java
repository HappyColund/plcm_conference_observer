package com.polycom.api.rest.plcm_conference_observer;


import java.io.Serializable;

public interface PlcmObject extends Serializable
{
   // Note: this is a tag class for DMA Plcm objects. All objects that
   // implement this interface must be
   // annotated with SimpleXml annotations. If the object is not annotated with
   // SimpleXml annotations,
   // the com.polycom.android.xml.BaseSerializer methods will fail.
}
