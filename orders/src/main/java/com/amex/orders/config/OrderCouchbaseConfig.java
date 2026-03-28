package com.amex.orders.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;

import com.couchbase.client.java.Cluster;

@Configuration
public class OrderCouchbaseConfig {

	@Bean("orderFactory")
	public CouchbaseClientFactory orderFactory(Cluster cluster) {
		return new SimpleCouchbaseClientFactory(cluster, "orders-bucket", "_default");
	}

	@Bean("orderTemplate")
	public CouchbaseTemplate orderTemplate(@Qualifier("orderFactory") CouchbaseClientFactory factory,
			MappingCouchbaseConverter converter) {

		return new CouchbaseTemplate(factory, converter);
	}
}