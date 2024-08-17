package br.com.procempa.bdrelationship.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procempa.bdrelationship.core.models.AutorModel;
import br.com.procempa.bdrelationship.core.repositories.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // generate crud get, getall, add, update, delete,
    public Iterable<AutorModel> getAll() {
        return autorRepository.findAll();
    }

    public AutorModel getAutorById(String id) {
        return autorRepository.findById(id).orElse(null);
    }

    public AutorModel postAutor(AutorModel entity) {
        return autorRepository.save(entity);
    }

    public AutorModel putAutor(AutorModel entity) {
        return autorRepository.save(entity);
    }

    public void deleteAutor(String id) {
        autorRepository.deleteById(id);
    }
    
}
