package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.ports.in.CreateGarmentUseCase;
import com.hiberus.users.domain.ports.out.GarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateGarment implements CreateGarmentUseCase {
    private final GarmentPort garmentPort;

    @Override
    public boolean createGarment(Garment garment){
        return garmentPort.createGarment(garment);
    }
}
