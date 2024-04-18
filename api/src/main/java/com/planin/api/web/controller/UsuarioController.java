package com.planin.api.web.controller;

import com.planin.api.repository.dao.UsuarioRepository;
import com.planin.api.repository.entity.Usuario;
import com.planin.api.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Usuario findById(@PathVariable("id") Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioService.findById(usuario.getId()));
        if (optionalUsuario.isEmpty()){
            return null;
        } else {
            return optionalUsuario.get();
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        if (id == null){
            return ResponseEntity.badRequest().build();
        }
        usuario.setId(id);
        usuarioService.save(usuario);
            return ResponseEntity.ok(usuario);
    }

    @PostMapping(path = "/crearUsuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        if (usuario == null){
            return ResponseEntity.badRequest().build();
        }
        usuarioService.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario){
        if (usuario.getId() == null || !usuarioRepository.existsById(usuario.getId())){
            return ResponseEntity.badRequest().build();
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping(path = "/{idUsuario}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long idUsuario){
        if (idUsuario == null || !usuarioRepository.existsById(idUsuario)){
            return ResponseEntity.badRequest().build();
        }
        usuarioRepository.deleteById(idUsuario);
        return new ResponseEntity("Usuario eliminado correctamente", HttpStatus.OK);
    }
}
