package com.cabservice.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    org.springframework.security.core.userdetails.User findUsername(String username);
}
