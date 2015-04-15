package com.trimc.blogger.solr.minimalist.dmo.base;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@SuppressWarnings("deprecation")
public abstract class PostDataBase {

	@Autowired
	@Qualifier("solrServer")
	private HttpSolrServer	server;

	public HttpSolrServer getServer() {
		return server;
	}

	public void setServer(HttpSolrServer server) {
		this.server = server;
	}
}
