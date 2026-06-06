package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class UsersPage {
    private List<User> users;
    private String header;
}
