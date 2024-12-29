package com.marhasoft.stock_control_api.models;

import com.marhasoft.stock_control_api.security.models.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String nome;

    @Column(length = 50)
    private String email;
}

