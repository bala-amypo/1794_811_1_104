package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    @GetMapping("/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord check(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId
    ) {
        return service.validateEligibility(employeeId, deviceItemId);
    }
}
