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
public class UserCouchbaseConfig {

	@Bean("userFactory")
	public CouchbaseClientFactory userFactory(Cluster cluster) {
		return new SimpleCouchbaseClientFactory(cluster, "user-bucket", "_default");
	}

	@Bean("userTemplate")
	public CouchbaseTemplate userTemplate(@Qualifier("userFactory") CouchbaseClientFactory factory,
			MappingCouchbaseConverter converter) {

		return new CouchbaseTemplate(factory, converter);
	}
}