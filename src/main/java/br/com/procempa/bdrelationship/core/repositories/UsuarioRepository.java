package br.com.procempa.bdrelationship.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.procempa.bdrelationship.core.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

}
