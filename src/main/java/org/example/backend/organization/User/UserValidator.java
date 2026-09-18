package org.example.backend.organization.User;

import org.example.backend.organization.Department.DepartmentRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final DepartmentRepository departmentRepository;

    public UserValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void validateUser(User user){
        if(!departmentRepository.existsById(user.getDepartment().getId()))
            throw new RuntimeException("Department not found");
    }
}
