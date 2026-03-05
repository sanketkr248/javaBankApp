package com.javabank.the_java_bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabank.the_java_bank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
	Boolean existsByAccountNumber(String accountNumber);
	
	User findByAccountNumber(String accountNumber);
	
	
}
