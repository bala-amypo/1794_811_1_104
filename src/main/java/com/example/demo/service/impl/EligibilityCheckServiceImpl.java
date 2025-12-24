@Override
public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
    EmployeeProfile emp = empRepo.findById(employeeId).orElseThrow();
    DeviceCatalogItem dev = devRepo.findById(deviceItemId).orElseThrow();
    
    boolean eligible = true;
    String reason = "Eligible"; // Use String, not StringBuilder

    if (!emp.getActive()) { 
        eligible = false; 
        reason = "Employee not active"; 
    } else if (dev.getActive() != null && !dev.getActive()) { 
        eligible = false; 
        reason = "Device inactive"; 
    } else if (!issueRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
        eligible = false;
        reason = "Active issuance already exists for this device";
    }

    EligibilityCheckRecord record = new EligibilityCheckRecord();
    record.setEmployeeId(employeeId);
    record.setDeviceItemId(deviceItemId);
    record.setIsEligible(eligible);
    record.setReason(reason);
    return checkRepo.save(record);
}