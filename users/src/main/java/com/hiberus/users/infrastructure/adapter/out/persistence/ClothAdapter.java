package com.hiberus.users.infrastructure.adapter.out.persistence;

import com.hiberus.users.domain.ports.out.ClothPort;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClothAdapter implements ClothPort {

    private final ClothClient clothClient;

    @Override
    public GarmentDTO getCloth(String id) {
        ResponseEntity<?>cloth=clothClient.buy(id);
        if(cloth.getStatusCode().is2xxSuccessful()){
            return (GarmentDTO) cloth.getBody();
        }
        if(cloth.getStatusCode().is4xxClientError()){
            throw new RuntimeException("Garment not found");
        }
        if(cloth.getStatusCode().is5xxServerError()){
            throw new RuntimeException("Internal server error");
        }
        throw new RuntimeException();
    }
}
