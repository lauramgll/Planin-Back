package com.planin.api.repository.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCuenta;

    private Long idCategoria;
    
    private LocalDate fecha;
    
    private String tipo;

    private Double importe;

    private String notas;
}