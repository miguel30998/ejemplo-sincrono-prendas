package com.hiberus.users.infrastructure.adapter.out.persistence;

import com.hiberus.users.domain.model.Size;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Cloth",url = "https://localhost:8090")
public interface ClothClient {


    @PutMapping(value="/clothing/buy/{garmentIdentifier}", produces = "application/json")
    GarmentDTO buy(@RequestParam String garmentIdentifier);

}
