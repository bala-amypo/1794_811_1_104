@Service
public class IssuedDeviceService {

    private final IssuedDeviceRepository issuedDeviceRepo;
    private final EmployeeRepository employeeRepo;
    private final DeviceItemRepository deviceItemRepo;

    public IssuedDeviceService(
            IssuedDeviceRepository issuedDeviceRepo,
            EmployeeRepository employeeRepo,
            DeviceItemRepository deviceItemRepo) {
        this.issuedDeviceRepo = issuedDeviceRepo;
        this.employeeRepo = employeeRepo;
        this.deviceItemRepo = deviceItemRepo;
    }

    // 🔹 ISSUE DEVICE LOGIC (THIS IS WHERE IT GOES)
    public IssuedDevice issueDevice(Long employeeId, Long deviceItemId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        DeviceItem deviceItem = deviceItemRepo.findById(deviceItemId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        IssuedDevice record = new IssuedDevice();

        // ✅ CORRECT PLACE
        record.setEmployee(employee);
        record.setDeviceItem(deviceItem);
        record.setIssuedDate(LocalDate.now());
        record.setReturnedDate(null);        // ✅ MUST BE NULL
        record.setStatus("ISSUED");

        return issuedDeviceRepo.save(record);
    }
}
