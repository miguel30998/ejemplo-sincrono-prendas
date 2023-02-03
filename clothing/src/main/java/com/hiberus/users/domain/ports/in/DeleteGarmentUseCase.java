package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.Size;

public interface DeleteGarmentUseCase {

    boolean deleteGarment(String name, Size size);
}
