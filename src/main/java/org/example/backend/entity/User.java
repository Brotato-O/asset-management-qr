package org.example.backend.entity;

import jakarta.persistence.*;
import org.example.backend.enums.UserRole;
import org.example.backend.enums.UserStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {
    enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "citizen_id", length = 20)
    private String citizenId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 20)
    private Gender gender;

    @Column(name = "salary", precision = 15, scale = 2)
    private BigDecimal salary;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role = UserRole.EMPLOYEE;

    @Column(name = "can_inspect")
    private Boolean canInspect = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private UserStatus status;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted", nullable = false, length = 3)
    private String isDeleted = "no";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name="password")
    private String password;

    // Constructors
    public User() {
    }

    public User(Integer id, String name, Department department, String address, String citizenId,
                Gender gender, BigDecimal salary, String email, String phone, UserRole role,
                Boolean canInspect, UserStatus status, LocalDateTime createdAt, String isDeleted, LocalDateTime deletedAt, String password) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.address = address;
        this.citizenId = citizenId;
        this.gender = gender;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.canInspect = canInspect;
        this.status = status;
        this.createdAt = createdAt;
        this.isDeleted= isDeleted;
        this.deletedAt= deletedAt;
        this.password= password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getCanInspect() {
        return canInspect;
    }

    public void setCanInspect(Boolean canInspect) {
        this.canInspect = canInspect;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}