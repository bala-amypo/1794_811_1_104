package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private Boolean active = true;

    public String getEmail() { 
        return email; 
        }
    public String getPasswordHash() {
         return passwordHash; 
         }
    public void setPasswordHash(String passwordHash) {
         this.passwordHash = passwordHash;
          }
    public String getRole() {
         return role; 
         }
    public void setRole(String role) {
         this.role = role; 
         }
    public void setActive(Boolean active) {
         this.active = active; 
         }
}
