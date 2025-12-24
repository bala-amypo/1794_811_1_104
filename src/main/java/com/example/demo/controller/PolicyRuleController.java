package com.example.demo.controller;

import com.example.demo.models.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
@Tag(name = "Policy Rules Endpoints")
public class PolicyRuleController {
    private final PolicyRuleService service;

    public PolicyRuleController(PolicyRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PolicyRule> create(@RequestBody PolicyRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<PolicyRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActive() {
        return ResponseEntity.ok(service.getActiveRules());
    }
}