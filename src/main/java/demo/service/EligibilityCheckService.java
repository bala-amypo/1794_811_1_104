package com.example.demo.service;

import com.example.demo.model.EligibilityCheckRecord;
import java.util.List;

public interface EligibilityCheckService {

    // USED BY CONTROLLER
    EligibilityCheckRecord checkEligibility(Long employeeId, Long deviceItemId);

    // USED BY TEST
    EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
