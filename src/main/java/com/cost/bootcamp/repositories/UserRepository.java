package com.cost.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cost.bootcamp.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
