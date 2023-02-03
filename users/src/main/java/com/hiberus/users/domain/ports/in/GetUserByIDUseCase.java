package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.User;

public interface GetUserByIDUseCase {
    User getUser(String id);
}
