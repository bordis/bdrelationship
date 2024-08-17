package br.com.procempa.bdrelationship.core.controllers;

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
import br.com.procempa.bdrelationship.core.services.BibliotecaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    // generate crud get, getall, add, update, delete,

    // get all libraries
    @GetMapping("")
    public ResponseEntity<Iterable<BibliotecaModel>> getBibliotecas() {
        Iterable<BibliotecaModel> bibliotecas = bibliotecaService.getAll();
        if (bibliotecas.iterator().hasNext()) {
            return ResponseEntity.ok(bibliotecas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get library by id
    @GetMapping("{id}")
    public ResponseEntity<BibliotecaModel> getBibliotecaById(@PathVariable String id) {
        BibliotecaModel biblioteca = bibliotecaService.getBibliotecaById(id);
        if (biblioteca != null) {
            return ResponseEntity.ok(biblioteca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // post method to add a new library, only admins can add libraries withou users
    @PostMapping("")
    public ResponseEntity<BibliotecaModel> postBiblioteca(@RequestBody BibliotecaModel biblioteca) {
        try {
            return ResponseEntity.ok(bibliotecaService.postBiblioteca(biblioteca));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(biblioteca);
        }
    }

    // post method to add a new library to a user
    @PostMapping("/{usuarioId}")
    public ResponseEntity<BibliotecaModel> postBiblioteca(@PathVariable String usuarioId,
            @RequestBody BibliotecaModel biblioteca) {
        try {
            return bibliotecaService.addUserToLibrary(biblioteca, usuarioId);
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    // put method to update library
    @PutMapping("{id}")
    public ResponseEntity<String> putBibliotecaById(@PathVariable String id, @RequestBody BibliotecaModel biblioteca) {
        BibliotecaModel oldBiblioteca = bibliotecaService.getBibliotecaById(id);
        if (oldBiblioteca != null) {
            bibliotecaService.postBiblioteca(biblioteca);
            return ResponseEntity.ok("Biblioteca atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete library by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBibliotecaById(@PathVariable String id) {
        try {
            bibliotecaService.deleteBiblioteca(id);
            return ResponseEntity.ok("Biblioteca deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar biblioteca");
        }
    }


}
