package com.example.demo.service;

import com.example.demo.model.EligibilityCheckRecord;

public interface EligibilityCheckService {
    EligibilityCheckRecord checkEligibility(Long employeeId, Long deviceId);
}
