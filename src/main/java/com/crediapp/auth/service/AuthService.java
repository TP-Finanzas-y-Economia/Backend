package com.crediapp.auth.service;

import com.crediapp.auth.dto.AuthResponse;
import com.crediapp.auth.dto.LoginRequest;
import com.crediapp.auth.dto.SignupRequest;
import com.crediapp.auth.entity.User;
import com.crediapp.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor; // Si usas Lombok para el constructor
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthResponse login(LoginRequest request) {

        var userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return new AuthResponse("Usuario no encontrado", null);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Contraseña incorrecta", null);
        }


        String token = jwtService.getToken(user);

        return new AuthResponse("Login exitoso", token);
    }

    public AuthResponse register(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse("El username ya existe", null);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("El email ya existe", null);
        }

        if (userRepository.existsByDni(request.getDni())) {
            return new AuthResponse("El DNI ya existe", null);
        }

        User user = User.builder()
                .username(request.getUsername())
                .dni(request.getDni())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);


        String token = jwtService.getToken(user);

        return new AuthResponse("Usuario registrado correctamente", token);
    }
}
