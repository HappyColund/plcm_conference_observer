package com.polycom.api.rest.common;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class InsecureHttpClientFactory {

	DefaultHttpClient hc = null;

	public DefaultHttpClient buildHttpClient() {
		hc = new DefaultHttpClient();
		// configureProxy();
		// configureCookieStore();
		configureSSLHandling();
		return hc;
	}

	@SuppressWarnings("unused")
	private void configureProxy() { // Here for documentation/future use
		HttpHost proxy = new HttpHost("proxy.example.org", 3182);
		hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

	@SuppressWarnings({ "unused" })
	private void configureCookieStore() { // Here for documentation/future use
		CookieStore cStore = new BasicCookieStore();
		hc.setCookieStore(cStore);
	}

	private void configureSSLHandling() {
		Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
		SSLSocketFactory sf = buildSSLSocketFactory();
		Scheme https = new Scheme("https", 443, (SchemeSocketFactory) sf);
		SchemeRegistry sr = hc.getConnectionManager().getSchemeRegistry();
		sr.register(http);
		sr.register(https);
	}

	private SSLSocketFactory buildSSLSocketFactory() {
		TrustStrategy ts = new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				return true;
			}
		};

		SSLSocketFactory sf = null;

		try {
			/* build socket factory with hostname verification turned off. */
			sf = new SSLSocketFactory(ts, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (Exception ex) {
			Logger.getLogger(InsecureHttpClientFactory.class.getName()).log(Level.SEVERE,
					"Failed to initialize SSL handling.", ex);
		}

		return sf;
	}
}