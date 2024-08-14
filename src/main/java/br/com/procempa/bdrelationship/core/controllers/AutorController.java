package br.com.procempa.bdrelationship.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.AutorModel;
import br.com.procempa.bdrelationship.core.services.AutorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // generate crud get, getall, add, update, delete, 

    @GetMapping("")
    public ResponseEntity<Iterable<AutorModel>> getAll() {
        Iterable<AutorModel> autores = autorService.getAll();
        if (autores.iterator().hasNext()) {
            return ResponseEntity.ok(autores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorModel> getAutorById(@PathVariable String id) {
        AutorModel autor = autorService.getAutorById(id);
        if (autor != null) {
            return ResponseEntity.ok(autor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PostMapping("")
    public ResponseEntity<AutorModel> postAutor(@RequestBody AutorModel autor) {
        try {
            // save entity
            return ResponseEntity.ok(autorService.postAutor(autor));
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorModel> putAutor(@PathVariable String id, @RequestBody AutorModel autor) {
        try {
            AutorModel oldAutor = autorService.getAutorById(id);
            if (oldAutor != null) {
                return ResponseEntity.ok(autorService.putAutor(autor));
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
            autorService.deleteAutor(id);
            return ResponseEntity.ok("Autor deletado com sucesso");
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }
    
    
}
