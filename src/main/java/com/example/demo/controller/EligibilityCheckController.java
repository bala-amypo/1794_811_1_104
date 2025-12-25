package com.example.demo.controller;

import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    @GetMapping
    public boolean checkEligibility(
            @RequestParam Long employeeId,
            @RequestParam Long deviceId
    ) {
        return service.isEligible(employeeId, deviceId);
    }
}
