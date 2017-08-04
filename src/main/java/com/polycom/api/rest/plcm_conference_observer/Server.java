
package com.polycom.api.rest.plcm_conference_observer;

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

public class Server extends AbstractBusTestServerBase {
	public static final String PORT = "9000";

	private static final String SERVER_CONFIG_FILE = "jaxrs-https-conference-observer-server.xml";

	protected void run() {
		SpringBusFactory bf = new SpringBusFactory();
		Bus bus = bf.createBus(SERVER_CONFIG_FILE);
		BusFactory.setDefaultBus(bus);
		InetAddress preferredAddress = null;

		try {
			preferredAddress = InetAddress.getLocalHost();
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress address = addresses.nextElement();
					if (address instanceof Inet4Address) {
						if (address.getAddress()[0] != 127) {
							preferredAddress = address;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in getting preferred address: " + e.getMessage());
		}

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(PlcmConferenceObserverResource.class);
		sf.setResourceProvider(PlcmConferenceObserverResource.class,
				new SingletonResourceProvider(new PlcmConferenceObserverResource()));
		String url = "https://" + preferredAddress.getHostAddress() + ":" + PORT + "/api/rest/notifications/conference";
		sf.setAddress(url);

		sf.create();
		System.out.println("Use consumer-url: " + url);
	}

	public static void main(String args[]) throws Exception {
		Server server = new Server();
		System.out.println("Conference observer starting...");
		server.start();

		Thread.sleep(1000 * 60 * 60 * 24);
		System.out.println("Conference observer shut down after one day.");
	}
}
