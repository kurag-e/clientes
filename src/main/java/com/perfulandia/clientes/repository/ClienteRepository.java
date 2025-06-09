package com.perfulandia.clientes.repository;

import com.perfulandia.clientes.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
}