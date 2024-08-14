package br.com.procempa.bdrelationship.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procempa.bdrelationship.core.models.UsuarioModel;
import br.com.procempa.bdrelationship.core.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // generate crud get, getall, add, update, delete,

    public Iterable<UsuarioModel> getAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel getUsuarioById(String id) {
        return usuarioRepository.findById(id).get();
    }

    public UsuarioModel postUsuario(UsuarioModel entity) {
        return usuarioRepository.save(entity);
    }
    
    public UsuarioModel putUsuario(UsuarioModel entity) {
        return usuarioRepository.save(entity);
    }

    public void deleteUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
    
}
