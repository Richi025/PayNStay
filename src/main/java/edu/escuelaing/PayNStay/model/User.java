package edu.escuelaing.PayNStay.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserType userType; 
    private LocalDate registrationDate;
    private List<String> roles;
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User(UUID id, String name, String email, String password, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.registrationDate = LocalDate.now();
    }

    public User() {
    }

    public User(String email, String password, List<String> roles ) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public enum UserType {
        OWNER, TENANT, AGENT;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
