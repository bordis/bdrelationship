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
import br.com.procempa.bdrelationship.core.services.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // post method to add a new user
    @PostMapping("")
    public ResponseEntity<UsuarioModel> postUsuario(@RequestBody UsuarioModel usuario) {
        try {
            return ResponseEntity.ok(usuarioService.postUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(usuario);
        }
    }

    // get all users
    @GetMapping("")
    public ResponseEntity<Iterable<UsuarioModel>> getUsuarios() {
        Iterable<UsuarioModel> usuarios = usuarioService.getAll();
        if (usuarios.iterator().hasNext()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get user by id
    @GetMapping("{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable String id) {
        UsuarioModel usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete user by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable String id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar usuario");
        }
    }

    // put method to update user
    @PutMapping("{id}")
    public ResponseEntity<String> putUsuarioById(@PathVariable String id, @RequestBody UsuarioModel usuario) {
        UsuarioModel oldUsuario = usuarioService.getUsuarioById(id);
        if (oldUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.postUsuario(usuario);
        return ResponseEntity.ok("Usuario atualizado com sucesso");
    }

}
