package com.springboot.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springapp.entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository <UserModel,Long>{

}
