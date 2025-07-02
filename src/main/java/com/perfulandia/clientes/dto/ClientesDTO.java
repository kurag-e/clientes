package com.perfulandia.clientes.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientesDTO extends RepresentationModel<ClientesDTO> {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
}
