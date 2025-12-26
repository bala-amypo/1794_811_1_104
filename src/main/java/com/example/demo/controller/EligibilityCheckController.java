package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    // POST /api/eligibility/validate/{employeeId}/{deviceItemId}
    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validateEligibility(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId
    ) {
        return service.validateEligibility(employeeId, deviceItemId);
    }

    // GET /api/eligibility/employee/{employeeId}
    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getChecksByEmployee(
            @PathVariable Long employeeId
    ) {
        return service.getChecksByEmployee(employeeId);
    }
}
