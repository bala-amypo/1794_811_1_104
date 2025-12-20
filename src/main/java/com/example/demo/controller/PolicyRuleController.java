package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.PolicyRule;
import com.example.demo.service.PolicyRuleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/policy-rules")
@Tag(name = "Policy Rules")
public class PolicyRuleController {

    private final PolicyRuleService policyRuleService;

    public PolicyRuleController(PolicyRuleService policyRuleService) {
        this.policyRuleService = policyRuleService;
    }

    @PostMapping
    public PolicyRule create(@RequestBody PolicyRule rule) {
        return policyRuleService.createRule(rule);
    }

    @GetMapping
    public List<PolicyRule> getAll() {
        return policyRuleService.getAllRules();
    }

    @GetMapping("/active")
    public List<PolicyRule> getActive() {
        return policyRuleService.getActiveRules();
    }
}
