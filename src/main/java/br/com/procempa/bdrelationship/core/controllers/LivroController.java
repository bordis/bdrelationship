package br.com.procempa.bdrelationship.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procempa.bdrelationship.core.models.LivroModel;
import br.com.procempa.bdrelationship.core.services.LivroService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // generate crud get, getall, add, update, delete,

    @GetMapping("")
    public ResponseEntity<Iterable<LivroModel>> getAll() {
        Iterable<LivroModel> livros = livroService.getAll();
        if (livros.iterator().hasNext()) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroModel> getLivroById(@PathVariable String id) {
        LivroModel livro = livroService.getLivroById(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<LivroModel> postLivro(@RequestBody LivroModel livro) {
        try {
            // save entity
            return ResponseEntity.ok(livroService.postLivro(livro));
        } catch (Exception e) {
            e.printStackTrace(); // Adiciona o stack trace para ajudar na depuração
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("path/{id}")
    public ResponseEntity<LivroModel> putLivro(@PathVariable String id, @RequestBody LivroModel livro) {
        LivroModel oldLivro = livroService.getLivroById(id);
        if (oldLivro != null) {
            livroService.postLivro(livro);
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLivroById(@PathVariable String id) {
        try {
            livroService.deleteLivro(id);
            return ResponseEntity.ok("Livro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar livro");
        }
    }
    
}
