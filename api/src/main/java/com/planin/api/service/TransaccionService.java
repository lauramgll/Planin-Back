package com.planin.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planin.api.repository.dao.TransaccionRepository;
import com.planin.api.repository.entity.Transaccion;

import jakarta.transaction.Transactional;

@Service
public class TransaccionService implements BaseService<Transaccion> {

    @Autowired
    TransaccionRepository transaccionRepository;

    @Override
    @Transactional
    public List<Transaccion> findAll() {
        List<Transaccion> transacciones = transaccionRepository.findAll();
        return transacciones;
    }

    @Override
    @Transactional
    public Transaccion findById(Long id) {
        Optional<Transaccion> optionalTransaccion =  transaccionRepository.findById(id);
        return optionalTransaccion.get();
    }

    @Override
    @Transactional
    public Transaccion save(Transaccion transaccion) {
        transaccion = transaccionRepository.save(transaccion);
        return transaccion;
    }

    @Override
    @Transactional
    public Transaccion update(Long id, Transaccion entity) {
        Optional<Transaccion> optionalTransaccion = transaccionRepository.findById(id);
        Transaccion transaccion = optionalTransaccion.get();
        transaccion = transaccionRepository.save(transaccion);
        return transaccion;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (transaccionRepository.existsById(id)){
            transaccionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public List<Transaccion> obtenerTransaccionesDeUsuario(Long usuarioId) {
        return transaccionRepository.findTransaccionesByUsuarioId(usuarioId);
    }

    /*
    OPCION 1: Enviar mes mayo 2024 y filtrar aqui y hacer la conversión a la fecha por la que filtrar...
    OPCION 2: Enviar un incremento junto con mes o anyo y sumar.
    Ejemplo:
    mes 3 -> Si la fecha atual es 14/05/24 -> 08/24
    anyo 5 -> 2029
     */
    @Transactional
    public List<Transaccion> transaccionesFiltros(Long usuarioId, String ft, Long fc) {
        LocalDate fecha = obtenerFechasFiltroTemporal(ft);
        if(ft.contains("mes")){
            if(fc != 0){
                return transaccionRepository.findByUserMonthCuenta(usuarioId, fecha.getMonthValue(), fecha.getYear(), fc);
            } else {
                return transaccionRepository.findByUserMonth(usuarioId, fecha.getMonthValue(), fecha.getYear());
            }
        }else {
            if(fc != 0){
                return transaccionRepository.findByUserAnyoCuenta(usuarioId, fecha.getYear(), fc);
            } else {
                return transaccionRepository.findByUserAnyo(usuarioId, fecha.getYear());
            }
            
        }
    }

    private LocalDate obtenerFechasFiltroTemporal(String ft){
        String[] palabras = ft.split("\\s+");
        if(ft.contains("mes")){
            String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
            // Obtener la última palabra
            String mes = palabras[palabras.length - 2];
            String anyo = palabras[palabras.length - 1];
            int posicion = -1; // Valor predeterminado si no se encuentra el mes
            for (int i = 0; i < meses.length; i++) {
                if (meses[i].equals(mes)) {
                    posicion = i;
                    break; // Salir del bucle una vez que se encuentre el mes
                }
            }
            return LocalDate.of(Integer.parseInt(anyo), posicion + 1, 1);
        } else {
            String anyo = palabras[palabras.length - 1];
            return LocalDate.of(Integer.parseInt(anyo), 1, 1);
        }
    }

    // Para prueba filtrado 2.0
    public List<Transaccion> getTransaccionesFiltradas(Long idCuenta, YearMonth fecha) {
        if (idCuenta != null && fecha != null) {
            return transaccionRepository.findByCuentaAndFecha(idCuenta, fecha.getYear(), fecha.getMonthValue());
        } else if (idCuenta != null) {
            return transaccionRepository.findByIdCuenta(idCuenta);
        } else if (fecha != null) {
            return transaccionRepository.findByFecha(fecha.getYear(), fecha.getMonthValue());
        } else {
            return transaccionRepository.findAll();
        }
    }
}
