package org.example.backend.service;

import org.example.backend.entity.User;
import org.example.backend.enums.UserStatus;
import org.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsActiveUser(int id) {
        return userRepository.existsByIdAndStatus(id, UserStatus.ACTIVE);
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

//    // Tạo User
//    public User createUser(User newUser) {
//        return userRepository.save(newUser);
//    }
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