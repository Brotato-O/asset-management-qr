package org.example.backend.organization.User;

import org.example.backend.enums.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder1;
    }

    public boolean existsActiveUser(int id) {
        return userRepository.existsByIdAndStatus(id, UserStatus.active);
    }

    // Lấy tất cả User đang ACTIVE
    public List<User> getActiveUsers() {
        return userRepository.findByIsDeleted("no");
    }

    // Lấy User đang ACTIVE theo ID
    public User getActiveUser(int id) {
        return userRepository.findByIdAndIsDeleted(id, "no")
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id
                        )
                );
    }

    // Tạo User
    public User createUser(User newUser) {
        userValidator.validateUser(newUser);
        String encodePassword= passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodePassword);
        return userRepository.save(newUser);
    }
//
//    // Cập nhật User
//    public User updateUser(int id, User newUser) {
//
//        User user = getActiveUser(id);
//
//        return userRepository.save(user);
//    }
//
//    // Vô hiệu hóa User
//    public User deactivateUser(int id) {
//
//        User user = getActiveUser(id);
//
//        user.setStatus(UserStatus.INACTIVE);
//
//        return userRepository.save(user);
//    }
}