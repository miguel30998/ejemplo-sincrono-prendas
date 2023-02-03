package com.hiberus.users.domain.ports.out;

import com.hiberus.users.infrastructure.DTO.GarmentDTO;

public interface ClothPort {
    GarmentDTO getCloth(String id);
}
