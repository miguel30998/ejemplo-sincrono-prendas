package com.hiberus.clothing.infrastructure.adapter.in.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ClothingController {

    @ApiOperation(value="Obtener prendas por id ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtenido con exito"),
            @ApiResponse(code = 400, message = "Error al obtener"),
            @ApiResponse(code = 404, message = "No existe"),
    })
    @GetMapping(value="/clothing/byId/{id}", produces = "application/json")
    ResponseEntity<String> getGarmentById(@PathVariable String id);

    @ApiOperation(value="Obtener prendas por familia")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtenido con exito"),
            @ApiResponse(code = 400, message = "Error al obtener")
    })
    @GetMapping(value="/clothing/byFamily/{family}", produces = "application/json")
    ResponseEntity<String> getGarmentByFamily(@PathVariable String family);

    @ApiOperation(value="Obtener prendas totales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtenido con exito"),
            @ApiResponse(code = 400, message = "Error al obtener"),
    })
    @GetMapping(value="/clothing", produces = "application/json")
    ResponseEntity<String> getClothing();
}
