package com.example.backend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Model.UserEntity;
import com.example.backend.Repository.UserRepository;

@Service
public class RegisterService {
    
    @Autowired private UserRepository register;
    public boolean register(String fullname, String email, String password) {
        Optional<UserEntity> optionalRegister = register.findByEmail(email);

        if(optionalRegister.isPresent()) {
            return false;
        }

        UserEntity newUser = new UserEntity(fullname, email, password);
        register.save(newUser);

        return true;
    }
}
