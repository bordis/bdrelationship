package br.com.procempa.bdrelationship.core.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bibliotecas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @ManyToMany
    @JoinTable(name = "biblioteca_usuario", joinColumns = @JoinColumn(name = "biblioteca_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @JsonIgnoreProperties("bibliotecas")
    private List<UsuarioModel> usuarios;

    public void setUsuario(UsuarioModel usuario) {
        this.usuarios.add(usuario);
    }

}
