package com.marhasoft.stock_control_api.security.controllers;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.models.Role;
import com.marhasoft.stock_control_api.security.repositories.PrivilegioRepository;
import com.marhasoft.stock_control_api.security.services.RoleService;
import com.marhasoft.stock_control_api.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class RoleController {

    private final PrivilegioRepository privilegioRepository;
    private final RoleService roleService;
    private final UsuarioService usuarioService;

    public RoleController(PrivilegioRepository privilegioRepository, RoleService roleService, UsuarioService usuarioService) {
        this.privilegioRepository = privilegioRepository;
        this.roleService = roleService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/roles")
    public List<Role> parameters(Model model) {
        return roleService.findAll();
    }

    @GetMapping("/role/{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping("/roles")
    public Role save(Role role) {
        return roleService.save(role);
    }

    @DeleteMapping("/role/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @PostMapping("/role/{roleId}/assign/usuario/{usuarioId}")
    public void assignUserRole(@PathVariable("roleId") Long roleId, @PathVariable("usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioByIdOrErro(usuarioId);
        roleService.assignUserRole(usuario, roleId);
    }

    @Transactional
    @DeleteMapping("/role/{roleId}/unAssign/usuario/{usuarioId}")
    public void unAssignUserRole(@PathVariable("roleId") Long roleId, @PathVariable("usuarioId") Long usuarioId) {
        roleService.unAssignUserRole(usuarioId, roleId);
    }

    @GetMapping("role/{roleId}/privilegios")
    public List<Privilegio> getPrivilegesInRole(@PathVariable("roleId") Long roleId) {
        return roleService.getPrivilegiosInRole(roleId);
    }

}
