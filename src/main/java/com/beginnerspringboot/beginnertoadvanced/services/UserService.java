package com.beginnerspringboot.beginnertoadvanced.services;

import com.beginnerspringboot.beginnertoadvanced.entites.User;
import com.beginnerspringboot.beginnertoadvanced.repositories.UserRepository;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?>  addUser(User user);
    public ResponseEntity<?> findUser(Long id);
    public ResponseEntity<?> updateUser(User user);
    public ResponseEntity<?> deleteUser(Long id);
}
