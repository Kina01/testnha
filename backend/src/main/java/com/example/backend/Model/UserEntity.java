package com.example.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String role;

    public UserEntity(String fullname, String email, String password) {
        this.fullname = fullname;  
        this.email = email;   
        this.password = password;
        this.role = "SV";
    }

    public UserEntity() {
        this.fullname = "";        
        this.password = "";
        this.email = "";
        this.role = "SV";
    }  
}