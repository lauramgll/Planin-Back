package com.planin.api.repository.dao;

import com.planin.api.repository.entity.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByIdUsuario(Long idUsuario);
}
