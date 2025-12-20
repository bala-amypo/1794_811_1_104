package com.example.demo.service;

import java.util.List;

import com.example.demo.models.PolicyRule;

public interface PolicyRuleService {

    PolicyRule createRule(PolicyRule rule);

    List<PolicyRule> getAllRules();

    List<PolicyRule> getActiveRules();
}
