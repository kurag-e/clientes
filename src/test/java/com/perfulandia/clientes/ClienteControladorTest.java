package com.perfulandia.clientes;

import com.perfulandia.clientes.controller.ClienteController;
import com.perfulandia.clientes.dto.ClientesDTO;
import com.perfulandia.clientes.services.Clienteservices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Clienteservices clienteService;

    @Test
    void testObtenerClientes() throws Exception {
        // Crear clientes DTO de prueba
        ClientesDTO cliente1 = new ClientesDTO();
        cliente1.setIdCliente(1);
        cliente1.setNombreCompleto("Ana");

        ClientesDTO cliente2 = new ClientesDTO();
        cliente2.setIdCliente(2);
        cliente2.setNombreCompleto("Luis");

        List<ClientesDTO> clientes = Arrays.asList(cliente1, cliente2);

        // Mockear respuesta del servicio
        when(clienteService.listar()).thenReturn(clientes);

        // Ejecutar GET y verificar respuesta con HATEOAS
        mockMvc.perform(get("/api/clientes")
                .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.clientesDTOList[0].nombreCompleto").value("Ana"))
                .andExpect(jsonPath("_embedded.clientesDTOList[1].nombreCompleto").value("Luis"))
                .andExpect(jsonPath("_links.self.href").exists()); 
    }
}
