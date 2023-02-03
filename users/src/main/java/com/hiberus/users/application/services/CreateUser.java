package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.User;
import com.hiberus.users.domain.ports.in.CreateUserUseCase;
import com.hiberus.users.domain.ports.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateUser implements CreateUserUseCase {
    private final UserPort userPort;

    @Override
    public boolean createUser(User user){
        return userPort.createUser(user);
    }
}
