package com.planin.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.planin.api.repository.dao.CategoriaRepository;
import com.planin.api.repository.entity.Categoria;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements BaseService<Categoria> {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    @Override
    @Transactional
    public Categoria findById(Long id) {
        Optional<Categoria> optionalCategoria =  categoriaRepository.findById(id);
        return optionalCategoria.get();
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        categoria = categoriaRepository.save(categoria);
        return categoria;
    }

    @Override
    @Transactional
    public Categoria update(Long id, Categoria entity) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        Categoria categoria = optionalCategoria.get();
        categoria = categoriaRepository.save(categoria);
        return categoria;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
