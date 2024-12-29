package com.marhasoft.stock_control_api.security.services;

import com.marhasoft.stock_control_api.repositories.UsuarioRepository;
import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.models.Role;
import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import com.marhasoft.stock_control_api.security.repositories.PrivilegioRepository;
import com.marhasoft.stock_control_api.security.repositories.RoleRepository;
import com.marhasoft.stock_control_api.security.repositories.UsuarioPrivilegioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final PrivilegioRepository privilegioRepository;
    private final UsuarioPrivilegioRepository usuarioPrivilegioRepository;

    public RoleService(RoleRepository roleRepository, UsuarioRepository usuarioRepository, PrivilegioRepository privilegioRepository, UsuarioPrivilegioRepository usuarioPrivilegioRepository) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.privilegioRepository = privilegioRepository;
        this.usuarioPrivilegioRepository = usuarioPrivilegioRepository;
    }

    //Get All Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //Get Role By Id
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    //Delete Role
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    //Update Role
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void assignUserRole(Long userId, Long roleId) {
        List<Privilegio> privileges = privilegioRepository.findByRoleId(roleId);

        List<UsuarioPrivilegio> existingUPrivilegio = usuarioPrivilegioRepository.findByUsuarioId(userId);

        usuarioPrivilegioRepository.deleteAll(existingUPrivilegio);

        List<UsuarioPrivilegio> assignments  = privileges.stream()
                .map(privilege -> new UsuarioPrivilegio(userId, privilege.getId()))
                .toList();

        usuarioPrivilegioRepository.saveAll(assignments);
    }

    @Transactional
    public void unAssignUserRole(Long userid, Long roleid) {
        List<Privilegio> privileges = privilegioRepository.findByRoleId(roleid);
        privileges.forEach(privilege -> usuarioPrivilegioRepository.deleteByUsuarioIdAndPrivilegioId(userid, privilege.getId()));
    }

    public List<Privilegio> getPrivilegiosInRole(Long roleId) {
        return privilegioRepository.findByRoleId(roleId);
    }
}
