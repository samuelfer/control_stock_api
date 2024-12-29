package com.marhasoft.stock_control_api.security.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Privilegio extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "A desrição é obrigatória")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "privilegio")
    @JsonIgnore
    private List<UsuarioPrivilegio> usuarios;
}
