package com.crediapp.auth.service;
import com.crediapp.auth.dto.AuthResponse;
import com.crediapp.auth.dto.LoginRequest;
import com.crediapp.auth.dto.SignupRequest;
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
    private final JWTService jwtService;

    public AuthResponse login(LoginRequest request) {

        var userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }


        String tokenReal = jwtService.getToken(user);


        return new AuthResponse(tokenReal);
    }

    public AuthResponse register(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El username ya existe");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }
        if (userRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("El DNI ya existe");
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
