package br.com.procempa.bdrelationship.core.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // post method to add a new library
    @PostMapping("/{usuario_id}")
    public ResponseEntity<BibliotecaModel> postBiblioteca(@RequestBody BibliotecaModel biblioteca, @RequestBody UsuarioModel usuario) {
        try{
            
            BibliotecaModel bibliotecaSalva = bibliotecaRepository.save(biblioteca);
            

            return ResponseEntity.ok(biblioteca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(biblioteca);
        }
    }

    //get all libraries
    @GetMapping("")
    public ResponseEntity<Iterable<BibliotecaModel>> getBibliotecas() {
        Iterable<BibliotecaModel> bibliotecas = bibliotecaRepository.findAll();
        if(bibliotecas.iterator().hasNext()){
            return ResponseEntity.ok(bibliotecas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //get library by id
    @GetMapping("{id}")
    public ResponseEntity<BibliotecaModel> getBibliotecaById(@PathVariable String id) {
        Optional<BibliotecaModel> biblioteca = bibliotecaRepository.findById(id);
        if(biblioteca.isPresent()){
            return ResponseEntity.ok(biblioteca.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    
    
}
