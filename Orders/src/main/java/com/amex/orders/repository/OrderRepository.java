package com.amex.orders.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import com.amex.orders.entity.Order;

@Repository
public interface OrderRepository extends CouchbaseRepository<Order, String> {

	@Query("#{#n1ql.selectEntity} WHERE userId = $1")
	List<Order> findByUserId(String userId);

}
