package com.planin.api.web.controller;

import com.planin.api.repository.dao.TransaccionRepository;
import com.planin.api.repository.entity.Transaccion;
import com.planin.api.service.TransaccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    TransaccionRepository transaccionRepository;

    // Listar transacciones
    @GetMapping
    public List<Transaccion> findAll(){
        return transaccionService.findAll();
    }

    // Listar transacción por id
    @GetMapping(path = "/{id}")
    public Transaccion findById(@PathVariable("id") Long id) {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(id);
        Optional<Transaccion> optionalTransaccion = Optional.ofNullable(transaccionService.findById(transaccion.getId()));
        if (optionalTransaccion.isEmpty()){
            return null;
        } else {
            return optionalTransaccion.get();
        }
    }

    // Actualizar transacción por id
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccion){
        if (id == null){
            return ResponseEntity.badRequest().build();
        }
        transaccion.setId(id);
        transaccionService.save(transaccion);
            return ResponseEntity.ok(transaccion);
    }

    // Crear transacción (1)
    @PostMapping(path = "/crearTransaccion")
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion){
        if (transaccion == null){
            return ResponseEntity.badRequest().build();
        }
        transaccionService.save(transaccion);
        return ResponseEntity.ok(transaccion);
    }

     // Crear transacción (varias)
    @PostMapping(path = "/crearTransacciones")
    public ResponseEntity<List<Transaccion>> crearTransacciones(@RequestBody List<Transaccion> transacciones) {
        if (transacciones == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Procesar cada transacción individualmente
        for (Transaccion transaccion : transacciones) {
            if (transaccion == null) {
                return ResponseEntity.badRequest().build();
            }
            transaccionService.save(transaccion);
        }
        
        return ResponseEntity.ok(transacciones);
    }

    // Actualizar transacción
    @PutMapping(path = "/")
    public ResponseEntity<Transaccion> actualizarTransaccion(@RequestBody Transaccion transaccion){
        if (transaccion.getId() == null || !transaccionRepository.existsById(transaccion.getId())){
            return ResponseEntity.badRequest().build();
        }
        transaccionRepository.save(transaccion);
        return ResponseEntity.ok(transaccion);
    }

    // Borrar transacción
    @DeleteMapping(path = "/{idTransaccion}")
    public ResponseEntity<Transaccion> eliminarTransaccion(@PathVariable Long idTransaccion){
        if (idTransaccion == null || !transaccionRepository.existsById(idTransaccion)){
            return ResponseEntity.badRequest().build();
        }
        transaccionRepository.deleteById(idTransaccion);
        return new ResponseEntity("Transacción eliminada correctamente", HttpStatus.OK);
    }

    // Transacciones por usuario
    @GetMapping(path = "/usuario/{idUsuario}")
    public ResponseEntity<List<Transaccion>> obtenerTransaccionesPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        List<Transaccion> transacciones = transaccionService.obtenerTransaccionesDeUsuario(idUsuario);
        if (transacciones == null || transacciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transacciones);
    }

    // Para filtrado
    @GetMapping(path = "/usuario/{idUsuario}/{ft}/{fc}")
    public ResponseEntity<List<Transaccion>> obtenerTransaccionesPorUsuario(
            @PathVariable("idUsuario") Long idUsuario,
            @PathVariable("ft") String ft,
            @PathVariable("fc") String fc) {
        List<Transaccion> transacciones = transaccionService.transaccionesFiltros(idUsuario, ft, fc);
        if (transacciones == null || transacciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transacciones);
    }
}
