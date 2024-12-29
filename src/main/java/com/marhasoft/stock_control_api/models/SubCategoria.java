package com.marhasoft.stock_control_api.models;

import com.fasterxml.jackson.annotation.*;
import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sub_categoria")
@Data
@JsonIgnoreProperties()
public class SubCategoria extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoria;
}
