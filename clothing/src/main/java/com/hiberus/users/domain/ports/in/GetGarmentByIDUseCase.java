package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.Garment;

public interface GetGarmentByIDUseCase {
    Garment getGarment(String id);
}
