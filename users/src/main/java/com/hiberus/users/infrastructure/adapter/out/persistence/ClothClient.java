package com.hiberus.users.infrastructure.adapter.out.persistence;

import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "clothing")
public interface ClothClient {


    @PutMapping(value="/clothing/buy/{garmentIdentifier}", produces = "application/json")
    ResponseEntity<GarmentDTO> buy(@RequestParam String garmentIdentifier);

}
