package org.example.backend.organization.User;

import org.example.backend.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByIdAndStatus(int id, UserStatus status);

    Optional<User> findByIdAndIsDeleted(int id, String isDeleted);

    List<User> findByIsDeleted(String isDeleted);

    Optional<User> findByEmailAndStatus(String email, UserStatus status);
}
