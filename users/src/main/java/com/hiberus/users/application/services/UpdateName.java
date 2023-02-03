package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.User;
import com.hiberus.users.domain.ports.in.UpdateNameUseCase;
import com.hiberus.users.domain.ports.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateName implements UpdateNameUseCase {
    private final UserPort garmentPort;


    @Override
    public boolean updateName(User user, String name) {
        user.setName(name);
        return garmentPort.createUser(user);
    }


}
