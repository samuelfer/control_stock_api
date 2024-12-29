package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ordem")
@Data
public class Ordem extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private Float subTotal = 0.0f;

    @Column(nullable = false)
    private Float itemDesconto = 0.0f;

    @Column(nullable = false)
    private Float taxa = 0.0f;

    @Column(nullable = false)
    private Float total = 0.0f;

    @Column(length = 50)
    private String promocao;

    @Column(nullable = false)
    private Float desconto = 0.0f;

    @OneToMany(mappedBy = "ordem")
    @JsonIgnore
    private List<OrdemItem> ordemItens;

    private int status;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", insertable = false, updatable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    private Cliente cliente;

}
