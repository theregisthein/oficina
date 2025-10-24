package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.OrdemServico;

public interface OrdemServicoService extends JpaRepository<OrdemServico, Long> {

}