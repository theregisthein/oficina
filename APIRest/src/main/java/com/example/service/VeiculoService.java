package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Veiculo;

public interface VeiculoService extends JpaRepository<Veiculo, Long> {

}