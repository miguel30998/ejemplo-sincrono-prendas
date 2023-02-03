package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.ports.in.GetGarmentByIDUseCase;
import com.hiberus.users.domain.ports.out.GarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class GetGarmentByID implements GetGarmentByIDUseCase {
    private final GarmentPort garmentPort;

    @Override
    public Garment getGarment(String id) {
        return garmentPort.getGarment(id);
    }
}
