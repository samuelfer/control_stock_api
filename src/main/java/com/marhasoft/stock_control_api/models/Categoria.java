package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.*;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
public class Categoria extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 4, message = "O nome precisa ter no mínimo 4 caracteres")
    private String nome;

    @Size(max = 500, message = "A descricao pode ter no máximo 500 caracteres")
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference
    @JsonIgnore
    private List<SubCategoria> subCategorias;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Produto> produtos;

}
