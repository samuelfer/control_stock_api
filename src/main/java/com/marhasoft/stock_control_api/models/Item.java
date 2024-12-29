package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Item extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @ManyToOne()
    @JoinColumn(name = "marca_id", insertable = false, updatable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", insertable = false, updatable = false)
    private Fornecedor fornecedor;

    @Column(length = 100)
    private String unidadeVenda;

    private Float desconto = 0.0f;

    private Float preco = 0.0f;

    private Short quantidade = 0;

    private Short quantidadeMinina;

    public void setQuantidade(Short quantidade) {
        if(quantidade < quantidadeMinina) {
            throw new IllegalArgumentException("Quantitade não pode ser negativa");
        }
        this.quantidade = quantidade;
    }
}
