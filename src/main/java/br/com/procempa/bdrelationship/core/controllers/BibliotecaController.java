package br.com.procempa.bdrelationship.core.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.BibliotecaModel;
import br.com.procempa.bdrelationship.core.models.UsuarioModel;
import br.com.procempa.bdrelationship.core.repositories.BibliotecaRepository;
import br.com.procempa.bdrelationship.core.repositories.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // generate crud get, getall, add, update, delete,

    // get all libraries
    @GetMapping("")
    public ResponseEntity<Iterable<BibliotecaModel>> getBibliotecas() {
        Iterable<BibliotecaModel> bibliotecas = bibliotecaRepository.findAll();
        if (bibliotecas.iterator().hasNext()) {
            return ResponseEntity.ok(bibliotecas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get library by id
    @GetMapping("{id}")
    public ResponseEntity<BibliotecaModel> getBibliotecaById(@PathVariable String id) {
        Optional<BibliotecaModel> biblioteca = bibliotecaRepository.findById(id);
        if (biblioteca.isPresent()) {
            return ResponseEntity.ok(biblioteca.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // post method to add a new library, only admins can add libraries withou users
    @PostMapping("")
    public ResponseEntity<BibliotecaModel> postBiblioteca(@RequestBody BibliotecaModel entity) {
        try {
            entity = bibliotecaRepository.save(entity);
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(entity);
        }
    }

    // post method to add a new library to a user
    @PostMapping("/{usuarioId}")
    public ResponseEntity<BibliotecaModel> postBiblioteca(@PathVariable String usuarioId,
            @RequestBody BibliotecaModel biblioteca) {
        try {

            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(usuarioId);
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            UsuarioModel usuario = usuarioOptional.get();

            // Inicializa a lista de usuários se estiver nula
            if (biblioteca.getUsuarios() == null) {
                biblioteca.setUsuarios(new ArrayList<>());
            }

            // Adiciona a biblioteca ao usuário e vice-versa
            biblioteca.getUsuarios().add(usuario);
            if (usuario.getBibliotecas() == null) {
                usuario.setBibliotecas(new ArrayList<>());
            }
            usuario.getBibliotecas().add(biblioteca);

            // Salva a biblioteca (cascading deve cuidar de salvar o relacionamento)
            BibliotecaModel bibliotecaSalva = bibliotecaRepository.save(biblioteca);

            return ResponseEntity.ok(bibliotecaSalva);
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    // put method to update library
    @PutMapping("{id}")
    public ResponseEntity<String> putBibliotecaById(@PathVariable String id, @RequestBody BibliotecaModel entity) {
        Optional<BibliotecaModel> biblioteca = bibliotecaRepository.findById(id);
        if (biblioteca.isPresent()) {
            bibliotecaRepository.save(entity);
            return ResponseEntity.ok("Biblioteca atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete library by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBibliotecaById(@PathVariable String id) {
        try {
            bibliotecaRepository.deleteById(id);
            return ResponseEntity.ok("Biblioteca deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar biblioteca");
        }
    }


}
