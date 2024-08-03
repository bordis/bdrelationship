package br.com.procempa.bdrelationship.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.procempa.bdrelationship.core.models.BibliotecaModel;

public interface BibliotecaRepository extends JpaRepository<BibliotecaModel, String> {

}
