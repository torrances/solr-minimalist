package com.trimc.blogger.solr.minimalist.dmo;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.solr.minimalist.dmo.base.PostDataBase;

@Component
@ComponentScan
public class PostData extends PostDataBase {

	public static LogManager	logger	= new LogManager(PostData.class);

	public void process(Collection<SolrInputDocument> docs) throws BusinessException {

		UpdateRequest req = new UpdateRequest();
		req.setAction(UpdateRequest.ACTION.COMMIT, false, false);
		req.add(docs);

		try {
			req.process(getServer());
		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Failed to post documents to server (url = %s)", getServer().getBaseURL());
		} catch (SolrServerException e) {
			logger.error(e);
			throw new BusinessException("Failed to post documents to server (url = %s)", getServer().getBaseURL());
		}
	}
}
