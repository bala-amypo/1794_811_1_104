package com.example.demo.repository;

import com.example.demo.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

/**
 * STEP 3 & 4 - UserAccountRepository
 * Extends JpaRepository to provide standard CRUD operations for the UserAccount entity.
 * Required for Authentication, JWT logic, and User Management.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    /**
     * Required for Spring Security's CustomUserDetailsService and AuthController login.
     * Exact naming required as per Section 4 of the Test Case Helper Document.
     * 
     * @param email The unique email of the user account.
     * @return An Optional containing the UserAccount if found.
     */
    Optional<UserAccount> findByEmail(String email);

    /**
     * Retrieves all user accounts registered in the system.
     * Provided by JpaRepository, but explicitly mentioned here as per repository requirements.
     * 
     * @return A list of all UserAccount entities.
     */
    @Override
    List<UserAccount> findAll();
}