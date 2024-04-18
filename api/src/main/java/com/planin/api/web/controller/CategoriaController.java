package com.planin.api.web.controller;

import com.planin.api.repository.dao.CategoriaRepository;
import com.planin.api.repository.entity.Categoria;
import com.planin.api.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Categoria findById(@PathVariable("id") Long id) {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        Optional<Categoria> optionalCategoria = Optional.ofNullable(categoriaService.findById(categoria.getId()));
        if (optionalCategoria.isEmpty()){
            return null;
        } else {
            return optionalCategoria.get();
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        if (id == null){
            return ResponseEntity.badRequest().build();
        }
        categoria.setId(id);
        categoriaService.save(categoria);
            return ResponseEntity.ok(categoria);
    }

    @PostMapping(path = "/crearCategoria")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }
        categoriaService.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria categoria){
        if (categoria.getId() == null || !categoriaRepository.existsById(categoria.getId())){
            return ResponseEntity.badRequest().build();
        }
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping(path = "/{idCategoria}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long idCategoria){
        if (idCategoria == null || !categoriaRepository.existsById(idCategoria)){
            return ResponseEntity.badRequest().build();
        }
        categoriaRepository.deleteById(idCategoria);
        return new ResponseEntity("Categoría eliminada correctamente", HttpStatus.OK);
    }
}
