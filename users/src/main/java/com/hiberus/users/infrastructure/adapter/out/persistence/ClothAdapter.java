package com.hiberus.users.infrastructure.adapter.out.persistence;

import com.hiberus.users.domain.ports.out.ClothPort;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class ClothAdapter implements ClothPort {

    private final ClothClient clothClient;

    @Override
    public GarmentDTO getCloth(String id) {
        return clothClient.buy(id);
    }
}
