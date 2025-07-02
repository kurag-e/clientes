package com.perfulandia.clientes;

import com.perfulandia.clientes.models.Clientes;
import com.perfulandia.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SuppressWarnings("unused")
@WebMvcTest

public class ClienteControladorTest {


    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepositorio;

    @Test
    void testObtenerClientes() throws Exception {
        // Crear datos de prueba
        Clientes cliente1 = new Clientes();
        cliente1.setIdUsuario(1);
        cliente1.setNombreCompleto("Ana");
        cliente1.setRut("11111111-1");
        cliente1.setDireccion("Calle Falsa 123");
        cliente1.setTelefono("123456789");

        Clientes cliente2 = new Clientes();
        cliente2.setIdUsuario(2);
        cliente2.setNombreCompleto("Luis");
        cliente2.setRut("22222222-2");
        cliente2.setDireccion("Calle Real 456");
        cliente2.setTelefono("987654321");




        Mockito.when(clienteRepositorio.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Ana"));
    }
}
