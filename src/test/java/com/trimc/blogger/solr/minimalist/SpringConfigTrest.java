package com.trimc.blogger.solr.minimalist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.solr.minimalist.dmo.PostData;

public class SpringConfigTrest {

	@Test
	public void init() throws Throwable {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		assertNotNull(ctx);

		PostData postData = (PostData) ctx.getBean("postData");
		assertNotNull(postData);
		
		assertNotNull(postData.getServer());
		assertTrue(postData.getServer().getBaseURL().startsWith("http://192.168"));
		assertTrue(postData.getServer().getBaseURL().endsWith("8983/solr/books"));
	}
}
