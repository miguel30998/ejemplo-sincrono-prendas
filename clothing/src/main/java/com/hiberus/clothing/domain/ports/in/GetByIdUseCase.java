package com.hiberus.clothing.domain.ports.in;

import com.hiberus.clothing.domain.model.Garment;

public interface GetByIdUseCase {
    Garment getGarment(String id);
}
