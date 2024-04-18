package com.planin.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.planin.api.repository.dao.UsuarioRepository;
import com.planin.api.repository.entity.Usuario;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements BaseService<Usuario> {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @Override
    @Transactional
    public Usuario findById(Long id) {
        Optional<Usuario> optionalUsuario =  usuarioRepository.findById(id);
        return optionalUsuario.get();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario entity) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        Usuario usuario = optionalUsuario.get();
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
