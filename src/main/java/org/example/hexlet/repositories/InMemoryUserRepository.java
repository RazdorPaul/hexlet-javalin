package org.example.hexlet.repositories;

import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository{
    private ArrayList<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public InMemoryUserRepository() {
        initUsers();
    }

    private void initUsers() {
        save(new User("Paul",
                "Razdorozhnyy",
                "kaisergars@gmail.com",
                "+79148001532",
                36,
                "pass1"));
        save(new User("John",
                "Doe",
                "john.doe@example.com",
                "+79246549385",
                25,
                "pass2"));
        save(new User("Jane",
                "Smith",
                "jane.smith@example.com",
                "+79245009338",
                30,
                "pass3"));
        save(new User("Bob",
                "Johnson",
                "bob.johnson@example.com",
                "+79245721110",
                35,
                "pass4"));
    }

    @Override
    public List<User> findByFirstNameStartingWith(String term) {
        if (term != null && !term.isBlank()) {
            var startFirstName = term.strip().toLowerCase();
            return users.stream().filter(
                    user -> user.
                            getFirstName() != null && user.getFirstName().
                            strip().
                            toLowerCase().
                            startsWith(startFirstName)
            ).toList();
        } else {
            return users;
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.
                stream().
                filter(user -> Objects.equals(user.getId(), id)).
                findFirst();
    }

    @Override
    public User save(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return users.removeIf(user -> Objects.equals(user.getId(), id));
    }
}
