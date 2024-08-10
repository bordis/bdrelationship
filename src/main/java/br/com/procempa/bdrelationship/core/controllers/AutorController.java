package br.com.procempa.bdrelationship.core.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.AutorModel;
import br.com.procempa.bdrelationship.core.repositories.AutorRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    // generate crud get, getall, add, update, delete, 

    @GetMapping("")
    public ResponseEntity<Iterable<AutorModel>> getAll() {
        Iterable<AutorModel> autores = autorRepository.findAll();
        if (autores.iterator().hasNext()) {
            return ResponseEntity.ok(autores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorModel> getAutorById(@PathVariable String id) {
        Optional<AutorModel> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PostMapping("")
    public ResponseEntity<AutorModel> postAutor(@RequestBody AutorModel entity) {
        try {
            // save entity
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorModel> putAutor(@PathVariable String id, @RequestBody AutorModel entity) {
        try {
            Optional<AutorModel> autor = autorRepository.findById(id);
            if (autor.isPresent()) {
                entity = autorRepository.save(entity);
                return ResponseEntity.ok(entity);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAutor(@PathVariable String id) {
        try {
            autorRepository.deleteById(id);
            return ResponseEntity.ok("Autor deletado com sucesso");
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }
    
    
}
