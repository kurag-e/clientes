package com.perfulandia.clientes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "clientesDTOList")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientesDTO extends RepresentationModel<ClientesDTO> {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
}
