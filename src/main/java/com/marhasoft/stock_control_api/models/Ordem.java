package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "ordem")
@Data
public class Ordem extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descricao;

    private Float subTotal = 0.0f;

    private Float itemDesconto = 0.0f;

    private Float taxa = 0.0f;

    private Float total = 0.0f;

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
