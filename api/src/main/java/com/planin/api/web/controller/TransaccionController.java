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

    @GetMapping
    public List<Transaccion> findAll(){
        return transaccionService.findAll();
    }

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

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccion){
        if (id == null){
            return ResponseEntity.badRequest().build();
        }
        transaccion.setId(id);
        transaccionService.save(transaccion);
            return ResponseEntity.ok(transaccion);
    }

    @PostMapping(path = "/crearTransaccion")
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion){
        if (transaccion == null){
            return ResponseEntity.badRequest().build();
        }
        transaccionService.save(transaccion);
        return ResponseEntity.ok(transaccion);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Transaccion> actualizarTransaccion(@RequestBody Transaccion transaccion){
        if (transaccion.getId() == null || !transaccionRepository.existsById(transaccion.getId())){
            return ResponseEntity.badRequest().build();
        }
        transaccionRepository.save(transaccion);
        return ResponseEntity.ok(transaccion);
    }

    @DeleteMapping(path = "/{idTransaccion}")
    public ResponseEntity<Transaccion> eliminarTransaccion(@PathVariable Long idTransaccion){
        if (idTransaccion == null || !transaccionRepository.existsById(idTransaccion)){
            return ResponseEntity.badRequest().build();
        }
        transaccionRepository.deleteById(idTransaccion);
        return new ResponseEntity("Transacción eliminada correctamente", HttpStatus.OK);
    }
}
