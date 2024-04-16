package com.jk.taxprep.taxprepsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.taxprep.taxprepsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
