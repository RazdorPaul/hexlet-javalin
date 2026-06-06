package org.example.hexlet.repositories;

import org.example.hexlet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findByFirstNameStartingWith(String term);
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    boolean delete(Long id);
}
