package com.amex.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Cluster;

@Configuration
public class CouchbaseClusterConfig {

	@Bean
	public Cluster couchbaseCluster() {
		return Cluster.connect("127.0.0.1", "Administrator", "Admin@5555");
	}
}