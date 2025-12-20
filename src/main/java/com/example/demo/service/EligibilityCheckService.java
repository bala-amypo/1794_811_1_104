package com.example.demo.service;

import com.example.demo.models.EligibilityCheckRecord;
import java.util.List;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;


public interface EligibilityCheckService {

    EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
