package com.amex.notification.service.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.amex.notification.service.entity.Notification;

public interface NotificationRepository extends CouchbaseRepository<Notification, String> {
}