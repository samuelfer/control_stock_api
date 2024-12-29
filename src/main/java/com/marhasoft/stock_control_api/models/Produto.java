package com.marhasoft.stock_control_api.models;

import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;

    @ManyToOne()
    @JoinColumn(name = "subcategoria_id", insertable = false, updatable = false)
    private SubCategoria subCategoria;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoria;
}

