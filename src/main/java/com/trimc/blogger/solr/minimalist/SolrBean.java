package com.trimc.blogger.solr.minimalist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:config/solr.properties")
public class SolrBean {

	@Value("${solr.allowcompression}")
	private boolean	allowcompression;

	@Value("${solr.connectiontimeout}")
	private Integer	connectiontimeout;
	
	@Value("${solr.core}")
	private String	core;
	
	@Value("${solr.followredirects}")
	private boolean	followredirects;

	@Value("${solr.host}")
	private String	host;

	@Value("${solr.maxconnectionsperhost}")
	private Integer	maxconnectionsperhost;

	@Value("${solr.maxretries}")
	private Integer	maxretries;

	@Value("${solr.maxtotalconnections}")
	private Integer	maxtotalconnections;

	@Value("${solr.port}")
	private Integer	port;

	@Value("${solr.socketreadtimeout}")
	private Integer	socketreadtimeout;

	public Integer getConnectiontimeout() {
		return connectiontimeout;
	}

	public String getCore() {
		return core;
	}

	public String getHost() {
		return host;
	}

	public Integer getMaxconnectionsperhost() {
		return maxconnectionsperhost;
	}

	public Integer getMaxretries() {
		return maxretries;
	}

	public Integer getMaxtotalconnections() {
		return maxtotalconnections;
	}

	public Integer getPort() {
		return port;
	}

	public Integer getSocketreadtimeout() {
		return socketreadtimeout;
	}
	
	public boolean isAllowcompression() {
		return allowcompression;
	}
	
	public boolean isFollowredirects() {
		return followredirects;
	}
	
	public void setAllowcompression(boolean allowcompression) {
		this.allowcompression = allowcompression;
	}

	public void setConnectiontimeout(Integer connectiontimeout) {
		this.connectiontimeout = connectiontimeout;
	}

	public void setCore(String core) {
		this.core = core;
	}

	public void setFollowredirects(boolean followredirects) {
		this.followredirects = followredirects;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setMaxconnectionsperhost(Integer maxconnectionsperhost) {
		this.maxconnectionsperhost = maxconnectionsperhost;
	}

	public void setMaxretries(Integer maxretries) {
		this.maxretries = maxretries;
	}

	public void setMaxtotalconnections(Integer maxtotalconnections) {
		this.maxtotalconnections = maxtotalconnections;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setSocketreadtimeout(Integer socketreadtimeout) {
		this.socketreadtimeout = socketreadtimeout;
	}
}
