package com.planin.api.repository.dao;

import com.planin.api.repository.entity.Transaccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :usuarioId")
    List<Transaccion> findTransaccionesByUsuarioId(@Param("usuarioId") Long usuarioId);
}
