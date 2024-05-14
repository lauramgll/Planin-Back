package com.planin.api.web.controller;

import com.planin.api.repository.dao.CuentaRepository;
import com.planin.api.repository.entity.Cuenta;
import com.planin.api.service.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    CuentaRepository cuentaRepository;

    // Listar cuentas
    @GetMapping
    public List<Cuenta> findAll(){
        return cuentaService.findAll();
    }

    // Listar cuentas por id
    @GetMapping(path = "/{id}")
    public Cuenta findById(@PathVariable("id") Long id) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(id);
        Optional<Cuenta> optionalCuenta = Optional.ofNullable(cuentaService.findById(cuenta.getId()));
        if (optionalCuenta.isEmpty()){
            return null;
        } else {
            return optionalCuenta.get();
        }
    }

    // Actualizar cuenta por id
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta){
        if (id == null){
            return ResponseEntity.badRequest().build();
        }
        cuenta.setId(id);
        cuentaService.save(cuenta);
            return ResponseEntity.ok(cuenta);
    }

    // Crear cuenta
    @PostMapping(path = "/crearCuenta")
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){
        if (cuenta == null){
            return ResponseEntity.badRequest().build();
        }
        cuentaService.save(cuenta);
        return ResponseEntity.ok(cuenta);
    }

    // Actualizar cuenta
    @PutMapping(path = "/")
    public ResponseEntity<Cuenta> actualizarCuenta(@RequestBody Cuenta cuenta){
        if (cuenta.getId() == null || !cuentaRepository.existsById(cuenta.getId())){
            return ResponseEntity.badRequest().build();
        }
        cuentaRepository.save(cuenta);
        return ResponseEntity.ok(cuenta);
    }

    // Borrar cuenta
    @DeleteMapping(path = "/{idCuenta}")
    public ResponseEntity<Cuenta> eliminarCuenta(@PathVariable Long idCuenta){
        if (idCuenta == null || !cuentaRepository.existsById(idCuenta)){
            return ResponseEntity.badRequest().build();
        }
        cuentaRepository.deleteById(idCuenta);
        return new ResponseEntity("Cuenta eliminada correctamente", HttpStatus.OK);
    }

    // Cuentas por usuario
    @GetMapping(path = "/usuario/{idUsuario}")
    public List<Cuenta> getCuentasByIdUsuario(@PathVariable Long idUsuario) {
        return cuentaService.getCuentasByIdUsuario(idUsuario);
    }
}
