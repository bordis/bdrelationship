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
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nome;

    @Column
    private String email;

    @ManyToMany(mappedBy = "usuarios", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("usuarios")
    private List<BibliotecaModel> bibliotecas;

    public void setBiblioteca(BibliotecaModel biblioteca) {
        this.bibliotecas.add(biblioteca);
    }

    
}
