package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Pessoa;

public interface PessoaService extends JpaRepository<Pessoa, Long> {

}