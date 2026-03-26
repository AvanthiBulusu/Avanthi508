package com.amex.orders.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.amex.orders.entity.User;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
}