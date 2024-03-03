package com.beginnerspringboot.beginnertoadvanced.restControllers;


import com.beginnerspringboot.beginnertoadvanced.entites.User;
import com.beginnerspringboot.beginnertoadvanced.services.servicesimpl.UserServicesImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServicesImpl userServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userServiceImpl.addUser(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userServiceImpl.updateUser(user);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getByIdUser(@RequestParam Long id) {
        return userServiceImpl.findUser(id);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return userServiceImpl.deleteUser(id);
    }
}
