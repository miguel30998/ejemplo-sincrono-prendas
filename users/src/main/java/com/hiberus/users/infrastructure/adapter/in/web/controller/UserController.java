package com.hiberus.users.infrastructure.adapter.in.web.controller;

import com.hiberus.users.infrastructure.DTO.PurchasesDTO;
import com.hiberus.users.infrastructure.DTO.UserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface UserController {

    @ApiOperation(value = "Crear un usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Creado con exito"),
            @ApiResponse(code = 400, message = "Ya existe usuario"),
            @ApiResponse(code = 400, message = "Error al crear")
    })
    @PostMapping(value = "/users", produces = "application/json")
    ResponseEntity<String> createUser(@RequestBody UserDTO user);

    @ApiOperation(value = "Eliminar un usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Eliminado con exito"),
            @ApiResponse(code = 200, message = "No encontrado el usuario"),
            @ApiResponse(code = 400, message = "Error al eliminar"),
    })
    @DeleteMapping(value = "/users/{id}", produces = "application/json")
    ResponseEntity<String> deleteUser(@RequestParam String id);

    @ApiOperation(value = "Obtener todos los usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtenidas con exito"),
            @ApiResponse(code = 400, message = "Error"),
    })
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<String> getUsers();

    @ApiOperation(value = "Obtener usuario por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtenidas con exito"),
            @ApiResponse(code = 404, message = "No existe"),
            @ApiResponse(code = 400, message = "Error obteniendo usuario")

    })
    @GetMapping(value = "/users/{id}", produces = "application/json")
    ResponseEntity<String> getUserById(@RequestParam String id);

    @ApiOperation(value = "Actualizar nombre de usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Actualizado con exito"),
            @ApiResponse(code = 404, message = "No existe"),
            @ApiResponse(code = 400, message = "Error al actualizar")

    })
    @PutMapping(value = "/users", produces = "application/json")
    ResponseEntity<String> updateName(@RequestBody UserDTO userDTO);

    @ApiOperation(value="Comprar un producto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comprado con exito"),
            @ApiResponse(code = 404, message = "No existe"),
            @ApiResponse(code = 400, message = "Error"),
            @ApiResponse(code = 400, message = "Cantidad insuficiente")

    })
    @PostMapping(value="/users/buy", produces = "application/json")
    ResponseEntity<String> buy(@RequestBody PurchasesDTO purchasesDTO);
}


