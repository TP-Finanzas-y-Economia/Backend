package com.crediapp.auth.service;
import com.crediapp.auth.entity.User;
import com.crediapp.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // READ: Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ: Obtener un usuario por su ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // UPDATE: Actualizar datos del usuario (ej. email o username)
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setDni(userDetails.getDni());
            // Nota: La contraseña no se suele actualizar por aquí por seguridad
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // DELETE: Eliminar un usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
