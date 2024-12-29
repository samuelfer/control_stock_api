package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.*;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
public class Categoria extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 75)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference
    @JsonIgnore
    private List<SubCategoria> subCategorias;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Produto> produtos;

}
