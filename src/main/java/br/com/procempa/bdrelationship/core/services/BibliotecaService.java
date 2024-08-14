package br.com.procempa.bdrelationship.core.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.procempa.bdrelationship.core.models.BibliotecaModel;
import br.com.procempa.bdrelationship.core.models.UsuarioModel;
import br.com.procempa.bdrelationship.core.repositories.BibliotecaRepository;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private UsuarioService usuarioService;

    // generate crud get, getall, add, update, delete,
    public Iterable<BibliotecaModel> getAll() {
        return bibliotecaRepository.findAll();
    }

    public BibliotecaModel getBibliotecaById(String id) {
        return bibliotecaRepository.findById(id).get();
    }

    public BibliotecaModel postBiblioteca(BibliotecaModel entity) {
        return bibliotecaRepository.save(entity);
    }

    public BibliotecaModel putBiblioteca(BibliotecaModel entity) {
        return bibliotecaRepository.save(entity);
    }

    public void deleteBiblioteca(String id) {
        bibliotecaRepository.deleteById(id);
    }

    // add user to library

    public ResponseEntity<BibliotecaModel> addUserToLibrary(BibliotecaModel biblioteca, String usuarioId) {
            UsuarioModel usuario = usuarioService.getUsuarioById(usuarioId);
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }

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
    }

    
    
    
}
