package br.com.procempa.bdrelationship.core.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.UsuarioModel;
import br.com.procempa.bdrelationship.core.repositories.UsuarioRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // post method to add a new user
    @PostMapping("")
    public ResponseEntity<UsuarioModel> postUsuario(@RequestBody UsuarioModel entity) {
        try {
            entity = usuarioRepository.save(entity);
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(entity);
        }
    }

    // get all users
    @GetMapping("")
    public ResponseEntity<Iterable<UsuarioModel>> getUsuarios() {
        Iterable<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.iterator().hasNext()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get user by id
    @GetMapping("{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete user by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable String id) {
        try {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuario deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar usuario");
        }
    }

    // put method to update user
    @PutMapping("{id}")
    public ResponseEntity<String> putUsuarioById(@PathVariable String id, @RequestBody UsuarioModel entity) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encotrado.");
        }
        usuarioRepository.save(entity);
        return ResponseEntity.ok("Usuario atualizado com sucesso");
    }

}
