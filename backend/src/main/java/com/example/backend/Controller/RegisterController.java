package com.example.backend.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.Model.UserEntity;
import com.example.backend.Service.RegisterService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.107:4200"}, 
            allowedHeaders = "*", 
            allowCredentials = "true", 
            methods = { RequestMethod.GET, 
                        RequestMethod.POST, 
                        RequestMethod.PUT, 
                        RequestMethod.DELETE })
public class RegisterController {
    
    @Autowired private RegisterService registerService;
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserEntity register) {
        try {
            boolean isRegistered = registerService.register(register.getFullname(), register.getEmail(), register.getPassword());
            Map<String, String> response = new HashMap<>();

            if (isRegistered) {
                response.put("message", "Đăng ký thành công!");
                response.put("status", "success");
                return ResponseEntity.ok(response);
            }
            response.put("message", "Tên đăng nhập đã tồn tại!");
            response.put("status", "error");
            return ResponseEntity.status(400).body(response);
            
        } catch(Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Lỗi hệ thống!");
            response.put("status", "error");
            return ResponseEntity.status(500).body(response);
        }
    }
}
