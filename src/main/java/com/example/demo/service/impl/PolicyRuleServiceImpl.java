package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.stereotype.Service;


@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository ruleRepository;

    public PolicyRuleServiceImpl(PolicyRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {

        ruleRepository.findByRuleCode(rule.getRuleCode())
                .ifPresent(r -> {
                    throw new BadRequestException("Rule code already exists");
                });

        return ruleRepository.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }
}
