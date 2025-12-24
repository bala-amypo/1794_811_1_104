package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.models.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {
    private final PolicyRuleRepository repository;

    public PolicyRuleServiceImpl(PolicyRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        if (repository.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code exists");
        }
        return repository.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}