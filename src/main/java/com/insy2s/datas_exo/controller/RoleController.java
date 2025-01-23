package com.insy2s.datas_exo.controller;

import com.insy2s.datas_exo.entity.Role;
import com.insy2s.datas_exo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        System.out.println("request to get all roles");
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getUserById(@PathVariable Long id){
        System.out.println("request to get role with id : "+id);
        return ResponseEntity.ok(roleService.getRoleById(id));
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Role body){
        System.out.println("request to create new role");
        roleService.createRole(body);
        return ResponseEntity.ok("Role created");
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Role body){
        System.out.println("request to update user by id");
        roleService.patchRole(id,body.getName());
    }
}
