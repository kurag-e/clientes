package com.perfulandia.clientes.dto;
import lombok.Data;

@Data
public class ClientesDTO {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
}