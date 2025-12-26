package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
