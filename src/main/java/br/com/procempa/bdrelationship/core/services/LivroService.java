package br.com.procempa.bdrelationship.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procempa.bdrelationship.core.models.LivroModel;
import br.com.procempa.bdrelationship.core.repositories.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    // generate crud get, getall, add, update, delete,

    public Iterable<LivroModel> getAll() {
        return livroRepository.findAll();
    }

    public LivroModel getLivroById(String id) {
        return livroRepository.findById(id).get();
    }

    public LivroModel postLivro(LivroModel entity) {
        return livroRepository.save(entity);
    }

    public LivroModel putLivro(LivroModel entity) {
        return livroRepository.save(entity);
    }

    public void deleteLivro(String id) {
        livroRepository.deleteById(id);
    }
    
    
}
