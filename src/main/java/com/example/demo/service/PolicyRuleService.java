package com.example.demo.service;

import com.example.demo.models.PolicyRule;
import java.util.List;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;


public interface PolicyRuleService {

    PolicyRule createRule(PolicyRule rule);

    List<PolicyRule> getAllRules();

    List<PolicyRule> getActiveRules();
}
