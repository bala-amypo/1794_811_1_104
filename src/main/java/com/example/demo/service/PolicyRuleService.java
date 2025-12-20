package com.example.demo.service;

import com.example.demo.models.PolicyRule;
import java.util.List;

public interface PolicyRuleService {

    PolicyRule create(PolicyRule rule);

    List<PolicyRule> getAll();

    PolicyRule getById(Long id);
}
