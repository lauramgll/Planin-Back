package com.planin.api.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planin.api.repository.entity.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :usuarioId")
    List<Transaccion> findTransaccionesByUsuarioId(@Param("usuarioId") Long usuarioId);

    // Para filtrado
    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :uid AND MONTH(t.fecha) = :mes AND YEAR (t.fecha) = :any AND c.id = :idCuenta")
    List<Transaccion> findByUserMonthCuenta(Long uid, int mes, int any, Long idCuenta);

    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :uid AND MONTH(t.fecha) = :mes AND YEAR (t.fecha) = :any")
    List<Transaccion> findByUserMonth(Long uid, int mes, int any);

    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :uid AND YEAR(t.fecha) = :any AND c.id = :idCuenta")
    List<Transaccion> findByUserAnyoCuenta(Long uid, int any, Long idCuenta);

    @Query("SELECT t FROM Transaccion t INNER JOIN Cuenta c ON t.idCuenta = c.id WHERE c.idUsuario = :uid AND YEAR(t.fecha) = :any")
    List<Transaccion> findByUserAnyo(Long uid, int any);
}
