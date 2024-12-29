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
    @JoinColumn(name = "usuario", insertable = false, updatable = false)
    private Usuario usuario;
    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "privilegio", insertable = false, updatable = false)
    private Privilegio privilegio;
    @Column(name = "privilegio_id")
    private Long privilegioId;

    public UsuarioPrivilegio(Long usuarioId, Long privilegioId) {
        this.usuarioId = usuarioId;
        this.privilegioId = privilegioId;
    }
}
