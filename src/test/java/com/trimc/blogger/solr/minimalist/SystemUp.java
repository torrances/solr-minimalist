package com.trimc.blogger.solr.minimalist;

import static org.junit.Assert.assertNotNull;

import java.net.Socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class SystemUp {

	public static boolean check() throws Throwable {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		assertNotNull(ctx);

		SolrBean solrBean = (SolrBean) ctx.getBean("solrBean");
		assertNotNull(solrBean);

		return serverListening(solrBean.getHost(), solrBean.getPort());
	}

	private static boolean serverListening(String host, int port) {
		Socket s = null;
		try {
			s = new Socket(host, port);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (s != null) try {
				s.close();
			} catch (Exception e) {}
		}
	}
}
