package com.beginnerspringboot.beginnertoadvanced.repositories;

import com.beginnerspringboot.beginnertoadvanced.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUserName(String userName);
}