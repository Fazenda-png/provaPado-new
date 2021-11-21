package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Dispositivos;

public interface DispositivoRepository extends JpaRepository<Dispositivos, String> {

}
