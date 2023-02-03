package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.User;

public interface UpdateNameUseCase {

    boolean updateName(User user, String name);
}
