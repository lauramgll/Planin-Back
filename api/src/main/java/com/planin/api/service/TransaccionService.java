package com.planin.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.planin.api.repository.dao.TransaccionRepository;
import com.planin.api.repository.entity.Transaccion;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public List<Transaccion> transaccionesFiltros(Long usuarioId, String ft, String fc) {
        LocalDate fecha = obtenerFechasFiltroTemporal(ft);
        if(ft.contains("mes")){
            return transaccionRepository.findByIdCuentaAndMonth(usuarioId, fecha.getMonthValue(), fecha.getYear());
        }else {
            return transaccionRepository.findByIdCuentaAndAnyo(usuarioId, fecha.getYear());
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
}
