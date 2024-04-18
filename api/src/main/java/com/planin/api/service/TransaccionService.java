package com.planin.api.service;

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
    
}
