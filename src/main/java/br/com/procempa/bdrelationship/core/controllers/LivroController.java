package br.com.procempa.bdrelationship.core.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.LivroModel;
import br.com.procempa.bdrelationship.core.repositories.LivroRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livro")
public class LivroController {


    @Autowired
    private LivroRepository livroRepository;

    // generate crud get, getall, add, update, delete,

    @GetMapping("")
    public ResponseEntity<Iterable<LivroModel>> getAll() {
        Iterable<LivroModel> livros = livroRepository.findAll();
        if (livros.iterator().hasNext()) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroModel> getLivroById(@PathVariable String id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<LivroModel> postLivro(@RequestBody LivroModel entity) {
        try {
            // save entity
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("path/{id}")
    public ResponseEntity<LivroModel> putLivro(@PathVariable String id, @RequestBody LivroModel entity) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.save(entity);
            return ResponseEntity.ok(entity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLivroById(@PathVariable String id) {
        try {
            livroRepository.deleteById(id);
            return ResponseEntity.ok("Livro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar livro");
        }
    }
    
}
