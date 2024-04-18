package com.planin.api.web.controller;

import com.planin.api.repository.dao.CategoriaRepository;
import com.planin.api.repository.entity.Categoria;
import com.planin.api.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
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

    // Listar categorías
    @GetMapping
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }

    // Listar categoría por id
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
}
