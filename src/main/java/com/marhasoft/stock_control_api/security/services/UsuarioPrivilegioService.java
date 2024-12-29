package com.marhasoft.stock_control_api.security.services;

import com.marhasoft.stock_control_api.models.Usuario;
import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import com.marhasoft.stock_control_api.security.repositories.UsuarioPrivilegioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioPrivilegioService {
    @Autowired
    public UsuarioPrivilegioRepository repository;

    public List<UsuarioPrivilegio> findAll() {
        return repository.findAll();
    }

    public UsuarioPrivilegio getPrivilege(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UsuarioPrivilegio save(UsuarioPrivilegio userPrivilegeAssignment) {
        return repository.save(userPrivilegeAssignment);
    }

    public void delete(Long id) {
        repository.deleteById(Long.valueOf(id));
    }

    public UsuarioPrivilegio getById(Long id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    public void update(UsuarioPrivilegio userPrivilegeAssignment) {
        repository.save(userPrivilegeAssignment);
    }

    @Transactional
    public List<Privilegio> savePrivilegios(List<Privilegio> privileges, Long userid) {
        repository.deleteByUsuarioId(userid);

        List<UsuarioPrivilegio> assignments = privileges.stream()
                .map(privilege -> new UsuarioPrivilegio(userid, privilege.getId()))
                .toList();

        return repository.saveAll(assignments).stream()
                .map(UsuarioPrivilegio::getPrivilegio)
                .toList();
    }

    public void deletePrivilegios(Long usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }

    public List<Privilegio> getUsuarioPrivilegios(Long usuarioId) {
        List<UsuarioPrivilegio> assignments = repository.findByUsuarioId(usuarioId);
        return assignments.stream()
                .map(UsuarioPrivilegio::getPrivilegio)
                .toList();
    }

    public List<Usuario> getUsuariosByPrivilegio(Long privilegioId) {
        List<UsuarioPrivilegio> assignments = repository.findByUsuarioId(privilegioId);
        return assignments.stream()
                .map(UsuarioPrivilegio::getUsuario)
                .toList();
    }
}
