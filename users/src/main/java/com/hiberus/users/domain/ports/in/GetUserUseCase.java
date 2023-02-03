package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.User;

import java.util.List;

public interface GetUserUseCase {
    List<User> getUsers();
}
