package com.example.demo.service;

import com.example.demo.models.EligibilityCheckRecord;
import java.util.List;

public interface EligibilityCheckService {

    EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
