package com.hiberus.users.application.services;

import com.hiberus.users.domain.ports.in.GetClothUseCase;
import com.hiberus.users.domain.ports.out.ClothPort;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class GetCloth implements GetClothUseCase {

    private final ClothPort clothPort;

    @Override
    public GarmentDTO getCloth(String id) {
        return clothPort.getCloth(id);
    }
}
