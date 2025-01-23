package com.insy2s.datas_exo.service;

import com.insy2s.datas_exo.entity.Role;
import com.insy2s.datas_exo.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    public void createRole(Role role){
        if(roleRepository.findByName(role.getName()).isEmpty())
            roleRepository.save(role);
    }

    public void patchRole(Long id, String name){
        Optional<Role> existingRole = roleRepository.findById(id);
        existingRole.ifPresent(role-> {
            if (name != null)
                role.setName(name);
            roleRepository.save(role);
        });
    }

    public void deleteRole(Role role){
        if(roleRepository.findByName(role.getName()).isPresent())
            roleRepository.delete(role);
    }
}
