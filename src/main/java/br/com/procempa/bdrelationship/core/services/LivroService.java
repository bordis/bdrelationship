package br.com.procempa.bdrelationship.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procempa.bdrelationship.core.models.AutorModel;
import br.com.procempa.bdrelationship.core.models.LivroModel;
import br.com.procempa.bdrelationship.core.repositories.AutorRepository;
import br.com.procempa.bdrelationship.core.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    // generate crud get, getall, add, update, delete,

    public Iterable<LivroModel> getAll() {
        return livroRepository.findAll();
    }

    public LivroModel getLivroById(String id) {
        return livroRepository.findById(id).orElse(null);
    }

    public LivroModel postLivro(LivroModel entity) {
        List<AutorModel> autoresAtualizados = new ArrayList<>();
        for (AutorModel autor : entity.getAutores()) {
            AutorModel autorExistente = autorRepository.findById(autor.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
            autoresAtualizados.add(autorExistente);
        }
        entity.setAutores(autoresAtualizados);
        return livroRepository.save(entity);
    }

    public LivroModel putLivro(LivroModel entity) {
        return livroRepository.save(entity);
    }

    public void deleteLivro(String id) {
        livroRepository.deleteById(id);
    }

}
