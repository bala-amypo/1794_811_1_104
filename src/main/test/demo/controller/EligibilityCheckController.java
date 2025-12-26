package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    @GetMapping("/check")
    public EligibilityCheckRecord checkEligibility(
            @RequestParam Long employeeId,
            @RequestParam Long deviceId
    ) {
        // ✅ correct method name
        return service.checkEligibility(employeeId, deviceId);
    }
}
