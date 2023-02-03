package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.User;

public interface CreateUserUseCase {

    boolean createUser(User user);

}
