/*****************************************************************************
 *
 * File:     Server.java 
 * $Revision: 87524 $ 
 *
 * (C) Polycom, Inc. 2012. All Rights Reserved 
 *     No portion of this work may be copied for any purpose without 
 *     the prior written permission of Polycom, Inc.
 *
 *****************************************************************************/

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.polycom.api.rest.plcm_participant_observer;

import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.testutil.common.AbstractBusTestServerBase;

public class Server extends AbstractBusTestServerBase
{
   public static final String PORT = "9001";

   private static final String SERVER_CONFIG_FILE =
         "jaxrs-https-participant-observer-server.xml";

   protected void run() 
   {
      SpringBusFactory bf = new SpringBusFactory();
      Bus bus = bf.createBus(SERVER_CONFIG_FILE);
      BusFactory.setDefaultBus(bus);
      InetAddress preferredAddress = null;

      try
      {
         preferredAddress = InetAddress.getLocalHost();

         Enumeration<NetworkInterface> interfaces = NetworkInterface
               .getNetworkInterfaces();
         while (interfaces.hasMoreElements()) 
         {
            Enumeration<InetAddress> addresses = interfaces.nextElement()
                  .getInetAddresses();
            while (addresses.hasMoreElements()) 
            {
               InetAddress address = addresses.nextElement();
               if (address instanceof Inet4Address) 
               {
                  if (address.getAddress()[0] != 127) 
                  {
                     preferredAddress = address;
                  }
               }
            }
         }
      }
      catch(Exception e)
      {
         System.out.println("Error in getting preferred address: " + e.getMessage());
      }
      JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
      sf.setResourceClasses(PlcmParticipantObserverResource.class);
      sf.setResourceProvider(PlcmParticipantObserverResource.class,
            new SingletonResourceProvider(
                  new PlcmParticipantObserverResource()));
      String url = "https://" + preferredAddress.getHostAddress()
            + ":" + PORT + "/api/rest/notifications/participant";
      sf.setAddress(url);

      sf.create();
      System.out.println("Use consumer-url: " + url);
      }

      public static void main(String args[]) throws Exception {
         Server server = new Server();
         System.out.println("Participant observer starting...");
         server.start();

         Thread.sleep(1000 * 60 * 60 * 24);
         System.out.println("Participant observer shut down after one day.");
      }
   }
