package com.crediapp.auth.service;
import com.crediapp.auth.dto.*;
import com.crediapp.auth.entity.User;
import com.crediapp.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor


public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {

        var userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return new AuthResponse("Usuario no encontrado");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Contraseña incorrecta");
        }

        return new AuthResponse("Login exitoso");
    }

    public AuthResponse register(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse("El username ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("El email ya existe");
        }

        if (userRepository.existsByDni(request.getDni())) {
            return new AuthResponse("El DNI ya existe");
        }

        User user = User.builder()
                .username(request.getUsername())
                .dni(request.getDni())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return new AuthResponse("Usuario registrado correctamente");
    }

}
