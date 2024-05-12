package com.mini.project.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.project.task.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
