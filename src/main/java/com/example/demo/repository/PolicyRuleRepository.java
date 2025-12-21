package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PolicyRule;

@Repository
public interface PolicyRuleRepository
        extends JpaRepository<PolicyRule, Long> {

    // REQUIRED BY TEST CASE
    List<PolicyRule> findByActiveTrue();

    // REQUIRED BY TEST CASE
    Optional<PolicyRule> findByRuleCode(String ruleCode);
}
