package com.cinema.api;

import com.cinema.api.dto.UserDTO;
import com.cinema.repo.model.User;
import com.cinema.repo.model.UserType;
import com.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id) {
        try {
            User user = userService.getUserByID(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Void> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/permission/{username}/{password}/{usertype}")
    public ResponseEntity<Void> getPermission(@PathVariable String username, @PathVariable String password, @PathVariable UserType usertype) {
        try {
            if(usertype == userService.getUsertypeByNamePass(username, password))
                return ResponseEntity.ok().build();
            throw new Exception("You don't have permission for that");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO user) {
        try {
            String username = user.getUsername();
            userService.getUserByUsername(username);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            String username = user.getUsername();
            String password = user.getPassword();
            long id = userService.create(username, password);
            return ResponseEntity.created(URI.create(String.format("/users/%d", id))).build();

        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUsertype(
            @PathVariable long id,
            @RequestBody String username,
            @RequestBody String password,
            @RequestBody UserType usertype) {
        try {
            if (userService.getUsertypeByNamePass(username, password) != UserType.ADMIN)
                throw new Exception("You don't have permission for that");
            userService.setUsertype(id, usertype);
            return ResponseEntity.noContent().build();

        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
