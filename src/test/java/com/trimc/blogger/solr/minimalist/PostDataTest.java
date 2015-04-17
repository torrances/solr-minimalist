package com.trimc.blogger.solr.minimalist;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trimc.blogger.commons.utils.MathUtils;
import com.trimc.blogger.solr.minimalist.dmo.PostData;

public final class PostDataTest {

	@Test
	public void update() throws Throwable {
		org.junit.Assume.assumeTrue(SystemUp.check());

		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id" + MathUtils.random(0, Integer.MAX_VALUE), 1.0f);
		doc1.addField("title", "The Yellow Admiral", 1.0f);
		doc1.addField("speaker", "Patrick O'Brian", 1.0f);
		doc1.addField("page", 56, 1.0f);
		doc1.addField("url", "http://en.wikipedia.org/wiki/The_Yellow_Admiral", 1.0f);
		doc1.addField("text", "the dark of the moon", 1.0f);

		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc1);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		assertNotNull(ctx);

		PostData postData = (PostData) ctx.getBean("postData");
		assertNotNull(postData);

		postData.process(docs);
	}
}
