package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.Garment;

import java.util.List;

public interface GetClothingUseCase {
    List<Garment> getClothing();
}
