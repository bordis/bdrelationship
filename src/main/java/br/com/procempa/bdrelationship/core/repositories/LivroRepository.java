package br.com.procempa.bdrelationship.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.procempa.bdrelationship.core.models.LivroModel;

public interface LivroRepository extends JpaRepository<LivroModel, String> {
    
}
