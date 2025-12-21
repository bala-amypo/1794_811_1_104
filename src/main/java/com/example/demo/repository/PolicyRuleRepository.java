package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PolicyRule;

@Repository
public interface PolicyRuleRepository
        extends JpaRepository<PolicyRule, Long> {

   
    List<PolicyRule> findByActiveTrue();

   
    Optional<PolicyRule> findByRuleCode(String ruleCode);
}
