package com.marhasoft.stock_control_api.security.services;

import com.marhasoft.stock_control_api.security.models.Privilegio;
import com.marhasoft.stock_control_api.security.repositories.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegioService {

    @Autowired
    private PrivilegioRepository privilegioRepository;

    public List<Privilegio> findAll(){
        return privilegioRepository.findAll();
    }

    public Privilegio getPrivilegio(int id){
        return privilegioRepository.findById((long) id).orElse(null);
    }

    public  Privilegio save(Privilegio privilegio){
        return privilegioRepository.save(privilegio);
    }

    public void delete(Integer id) {
        privilegioRepository.deleteById(Long.valueOf(id));
    }

    public Privilegio getById(Integer id) {
        return privilegioRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public void update(Privilegio privilegio) {
        privilegioRepository.save(privilegio);
    }

}
