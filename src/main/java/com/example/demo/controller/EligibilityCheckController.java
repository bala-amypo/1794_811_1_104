package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/eligibility")
@Tag(name = "Eligibility Checks")
public class EligibilityCheckController {

    private final EligibilityCheckService eligibilityService;

    public EligibilityCheckController(EligibilityCheckService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validate(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId) {

        return eligibilityService.validateEligibility(employeeId, deviceItemId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getByEmployee(@PathVariable Long employeeId) {
        return eligibilityService.getChecksByEmployee(employeeId);
    }
}
