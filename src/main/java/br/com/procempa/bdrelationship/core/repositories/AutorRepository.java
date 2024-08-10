package br.com.procempa.bdrelationship.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.procempa.bdrelationship.core.models.AutorModel;

public interface AutorRepository extends JpaRepository<AutorModel, String> {
    
}
