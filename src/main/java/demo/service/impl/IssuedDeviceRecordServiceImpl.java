@Override
public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
    return issuedRepo.save(record);
}

@Override
public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
    return issuedRepo.findByEmployeeId(employeeId);
}
