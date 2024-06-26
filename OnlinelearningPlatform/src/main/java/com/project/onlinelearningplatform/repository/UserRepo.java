package com.project.onlinelearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.project.onlinelearningplatform.model.User;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
