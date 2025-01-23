package com.insy2s.datas_exo.service;

import com.insy2s.datas_exo.entity.Address;
import com.insy2s.datas_exo.entity.Role;
import com.insy2s.datas_exo.entity.User;
import com.insy2s.datas_exo.repository.IAddressRepository;
import com.insy2s.datas_exo.repository.IRoleRepository;
import com.insy2s.datas_exo.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final IAddressRepository addressRepository;
    private final IRoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void createUser(User user){
        if(user.getAddress()!=null){
            Address address = user.getAddress();
            Optional<Address> existingAddress = addressRepository.findAddressByStreetNumberAndStreetNameAndCity(address.getStreetNumber(), address.getStreetName(), address.getCity());
            if(existingAddress.isPresent()){
                user.setAddress(existingAddress.get());
            }else {
                addressRepository.save(address);
            }
        }
        if(user.getRoles()!=null){
            List<Role> roles = user.getRoles();
            for(Role role : roles){
                Optional<Role> existingRole = roleRepository.findByName(role.getName());
                existingRole.ifPresent(value -> role.setId(value.getId()));
            }
        }
        userRepository.save(user);
    }

    public void updateUserLastname(Long id, String lastname){
        Optional<User> existingUser = userRepository.findById(id);
        existingUser.ifPresent(user->{
            if(lastname != null)
                user.setLastname(lastname);
            userRepository.save(user);
        });
    }

    public void deleteUser(Long id){
        Optional<User> existingUser = userRepository.findById(id);
        existingUser.ifPresent(userRepository::delete);
    }
}
