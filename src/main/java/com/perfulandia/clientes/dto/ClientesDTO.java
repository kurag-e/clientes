package com.perfulandia.clientes.dto;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import com.fasterxml.jackson.annotation.JsonInclude;


@Data
public class ClientesDTO extends RepresentationModel<ClientesDTO> {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
}