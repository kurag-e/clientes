package com.perfulandia.clientes.controller;

import com.perfulandia.clientes.dto.ClientesDTO;
import com.perfulandia.clientes.services.Clienteservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clienteservices service;

    // Crear cliente
    @PostMapping
    public ResponseEntity<ClientesDTO> crear(@RequestBody ClientesDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    // Listar todos los clientes con links HATEOAS
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ClientesDTO>>> listarClientes() {
        List<ClientesDTO> clientes = service.listar();

        List<EntityModel<ClientesDTO>> clienteModels = clientes.stream()
            .map(cliente -> EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).getCliente(cliente.getIdCliente())).withSelfRel()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(clienteModels,
                linkTo(methodOn(ClienteController.class).listarClientes()).withSelfRel())
        );
    }

    // Obtener cliente por ID con links HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClientesDTO>> getCliente(@PathVariable Integer id) {
        return service.obtenerPorId(id)
            .map(cliente -> EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).getCliente(id)).withSelfRel(),
                linkTo(methodOn(ClienteController.class).listarClientes()).withRel("todos")))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClientesDTO> actualizar(@PathVariable Integer id, @RequestBody ClientesDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
