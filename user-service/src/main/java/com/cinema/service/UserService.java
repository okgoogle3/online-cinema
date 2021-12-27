package com.cinema.service;

import com.cinema.repo.UserRepo;
import com.cinema.repo.model.User;
import com.cinema.repo.model.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {
    public final UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByID(long id) throws Exception {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) {
            throw new Exception();
        } else {
            return user.get();
        }
    }

    public User getUserByUsername(String username) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepo.findByUsername(username));
        if (user.isEmpty()) {
            throw new Exception("This user does not exist");
        } else {
            return user.get();
        }
    }

    public UserType getUsertypeByNamePass(String username, String password) throws Exception {
        Optional<User> maybeUser = Optional.ofNullable(userRepo.findByUsername(username));
        if (maybeUser.isEmpty()) {
            return null;
        } else {
            User user = maybeUser.get();
            if (user.getPassword().equals(password)) return user.getType();
            else throw new Exception("You don't have permission to do this");
        }
    }

    public void setUsertype(long id, UserType usertype) throws Exception {
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new Exception();
        User user = maybeUser.get();
        user.setType(usertype);
        userRepo.save(user);
    }

    public long create(String username, String password) {

        User user = new User(username, password);
        User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public void delete(long id) {
        userRepo.deleteById(id);
    }
}
