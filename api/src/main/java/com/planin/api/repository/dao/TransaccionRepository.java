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
    /*
    // Para prueba filtrado 2.0
    @Query("SELECT t FROM Transaccion t WHERE (:cuenta IS NULL OR t.idCuenta = :cuenta) " +
    "AND (:year IS NULL OR YEAR(t.fecha) = :year) " +
    "AND (:month IS NULL OR MONTH(t.fecha) = :month)")
    List<Transaccion> findByCuentaAndFecha(@Param("cuenta") Long cuenta,
                                     @Param("year") Integer year,
                                     @Param("month") Integer month);

    List<Transaccion> findByIdCuenta(Long cuenta); 

    @Query("SELECT t FROM Transaccion t WHERE YEAR(t.fecha) = :year AND MONTH(t.fecha) = :month")
    List<Transaccion> findByFecha(@Param("year") Integer year, @Param("month") Integer month);
    */
}
