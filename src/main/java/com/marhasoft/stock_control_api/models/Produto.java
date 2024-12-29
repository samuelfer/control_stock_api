package com.marhasoft.stock_control_api.models;

import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 75)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne()
    @JoinColumn(name = "subcategoria_id", insertable = false, updatable = false)
    private SubCategoria subCategoria;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoria;
}

