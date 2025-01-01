package com.marhasoft.stock_control_api.security.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marhasoft.stock_control_api.models.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "usuario_privilegio",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "privilegio_id"})})
public class UsuarioPrivilegio {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "privilegio_id")
    private Privilegio privilegio;


    public UsuarioPrivilegio(Usuario usuario, Privilegio privilegio) {
        this.usuario = usuario;
        this.privilegio = privilegio;
    }
}
