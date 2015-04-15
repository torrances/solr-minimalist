package com.trimc.blogger.solr.minimalist;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.trimc.blogger.commons.LogManager;

@Configuration
@SuppressWarnings("deprecation")
@ComponentScan("com.trimc.blogger.solr.minimalist")
public class SpringConfig {

	public static LogManager	logger	= new LogManager(SpringConfig.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Qualifier("solrBean")
	public SolrBean getSolrBean() {
		return new SolrBean();
	}

	public @Bean HttpSolrServer solrServer() throws Exception {
		final String template = "http://%s:%s/solr/%s";

		String url = String.format(template, getSolrBean().getHost(), getSolrBean().getPort(), getSolrBean().getCore());
		logger.info("Constructed URL from SolrBean config (%s)", url);

		HttpSolrServer server = new HttpSolrServer(url);

		server.setMaxRetries(getSolrBean().getMaxretries()); // defaults to 0.  > 1 not recommended.
		server.setConnectionTimeout(getSolrBean().getConnectiontimeout()); // 5 seconds to establish TCP
		// Setting the XML response parser is only required for cross
		// version compatibility and only when one side is 1.4.1 or
		// earlier and the other side is 3.1 or later.
		server.setParser(new XMLResponseParser()); // binary parser is used by default
		// The following settings are provided here for completeness.
		// They will not normally be required, and should only be used 
		// after consulting javadocs to know whether they are truly required.
		server.setSoTimeout(getSolrBean().getSocketreadtimeout()); // socket read timeout
		server.setDefaultMaxConnectionsPerHost(getSolrBean().getMaxconnectionsperhost());
		server.setMaxTotalConnections(getSolrBean().getMaxtotalconnections());
		server.setFollowRedirects(getSolrBean().isFollowredirects()); // defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any effect.
		server.setAllowCompression(getSolrBean().isAllowcompression());

		return server;
	}
}
