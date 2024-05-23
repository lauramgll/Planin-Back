package com.planin.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.planin.api.repository.dao.CuentaRepository;
import com.planin.api.repository.entity.Cuenta;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CuentaService implements BaseService<Cuenta> {

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    @Transactional
    public List<Cuenta> findAll() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentas;
    }

    @Override
    @Transactional
    public Cuenta findById(Long id) {
        Optional<Cuenta> optionalCuenta =  cuentaRepository.findById(id);
        return optionalCuenta.get();
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        cuenta = cuentaRepository.save(cuenta);
        return cuenta;
    }

    @Override
    @Transactional
    public Cuenta update(Long id, Cuenta entity) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        Cuenta cuenta = optionalCuenta.get();
        cuenta = cuentaRepository.save(cuenta);
        return cuenta;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (cuentaRepository.existsById(id)){
            cuentaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public List<Cuenta> getCuentasByIdUsuario(Long idUsuario) {
        return cuentaRepository.findByIdUsuario(idUsuario);
    }

    // Para filtrado
    @Transactional
    public Cuenta findByNombre(String nombre){
        return cuentaRepository.findByNombre(nombre);
    }
}
