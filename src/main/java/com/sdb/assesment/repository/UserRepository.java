package com.sdb.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdb.assesment.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByUsername(String username);

}
