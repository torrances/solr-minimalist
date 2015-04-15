package com.trimc.blogger.solr.minimalist;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

@SuppressWarnings("deprecation")
public final class PostData {

	public static void main(String... args) throws Throwable {

		String url = "http://192.168.1.34:8983/solr/books";

		HttpSolrServer server = new HttpSolrServer(url);
		assertNotNull(server);

		server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP
		// Setting the XML response parser is only required for cross
		// version compatibility and only when one side is 1.4.1 or
		// earlier and the other side is 3.1 or later.
		server.setParser(new XMLResponseParser()); // binary parser is used by default
		// The following settings are provided here for completeness.
		// They will not normally be required, and should only be used 
		// after consulting javadocs to know whether they are truly required.
		server.setSoTimeout(1000); // socket read timeout
		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(100);
		server.setFollowRedirects(false); // defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any effect.
		server.setAllowCompression(true);

		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id2", 1.0f);
		doc1.addField("title", "The Yellow Admiral", 1.0f);
		doc1.addField("speaker", "Patrick O'Brian", 1.0f);
		doc1.addField("page", 56, 1.0f);
		doc1.addField("url", "http://en.wikipedia.org/wiki/The_Yellow_Admiral", 1.0f);
		doc1.addField("line", "the dark of the moon", 1.0f);

		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc1);

		UpdateRequest req = new UpdateRequest();
		req.setAction(UpdateRequest.ACTION.COMMIT, false, false);
		req.add(docs);

		UpdateResponse rsp = req.process(server);
		assertNotNull(rsp);
	}
}
