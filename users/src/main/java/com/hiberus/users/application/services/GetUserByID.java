package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.User;
import com.hiberus.users.domain.ports.in.GetUserByIDUseCase;
import com.hiberus.users.domain.ports.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class GetUserByID implements GetUserByIDUseCase {
    private final UserPort userPort;

    @Override
    public User getUser(String id) {
        return userPort.getUser(id);
    }
}
