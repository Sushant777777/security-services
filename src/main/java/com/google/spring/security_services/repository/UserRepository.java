package com.google.spring.security_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.spring.security_services.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName(String userName);
}
