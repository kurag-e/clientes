package com.perfulandia.clientes.controller;

import com.perfulandia.clientes.dto.ClientesDTO;
import com.perfulandia.clientes.services.Clienteservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private Clienteservices service;

   
    @PostMapping
    public ClientesDTO crear(@RequestBody ClientesDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/hateoas/{id}")
    public ClientesDTO obtenerHATEOAS(@PathVariable Integer id) {
        ClientesDTO dto = service.obtenerPorId(id).orElse(null);
        if (dto != null) {
            dto.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(id)).withSelfRel());
            dto.add(linkTo(methodOn(ClienteController.class).obtenerTodosHATEOAS()).withRel("todos"));
            dto.add(linkTo(methodOn(ClienteController.class).eliminar(id)).withRel("eliminar"));
        }
        return dto;
    }


    @GetMapping("/hateoas")
    public List<ClientesDTO> obtenerTodosHATEOAS() {
        List<ClientesDTO> lista = service.listar();
        for (ClientesDTO dto : lista) {
            dto.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(dto.getIdCliente())).withSelfRel());
        }
        return lista;
    }

  
    @GetMapping("/{id}")
    public ClientesDTO getCliente(@PathVariable Integer id) {
        return service.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ClientesDTO actualizar(@PathVariable Integer id, @RequestBody ClientesDTO dto) {
        return service.actualizar(id, dto).orElse(null);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
