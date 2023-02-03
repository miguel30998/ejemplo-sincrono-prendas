package com.hiberus.users.infrastructure.DTO;

import com.hiberus.users.domain.model.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarmentDTO {
    private String name;
    private int quantity;
    private Size size;
}
