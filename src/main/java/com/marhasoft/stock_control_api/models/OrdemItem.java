package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ordem_item")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdemItem extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Ordem ordem;

    @Column(nullable = false)
    private Float desconto = 0.0f;

    @Column(nullable = false)
    private Short quantidade = 0;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}

