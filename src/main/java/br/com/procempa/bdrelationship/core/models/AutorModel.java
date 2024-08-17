package br.com.procempa.bdrelationship.core.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nome;

    @ManyToMany(mappedBy = "autores", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("autores")
    private List<LivroModel> livros;
    
}
