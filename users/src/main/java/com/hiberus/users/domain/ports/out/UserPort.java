package com.hiberus.users.domain.ports.out;

import com.hiberus.users.domain.model.User;

import java.util.List;

public interface UserPort {



    boolean createUser(User user);

    boolean deleteUser(String dni);

    List<User> getUsers();

    User getUser(String id);
}
