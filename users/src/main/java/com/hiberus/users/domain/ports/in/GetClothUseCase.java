package com.hiberus.users.domain.ports.in;

import com.hiberus.users.infrastructure.DTO.GarmentDTO;

public interface GetClothUseCase {

    GarmentDTO getCloth(String id);
}
