@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    // ✅ FIXED: POST + /validate
    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validateEligibility(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId) {

        return service.validateEligibility(employeeId, deviceItemId);
    }

    // ✅ REQUIRED by spec
    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getChecksByEmployee(
            @PathVariable Long employeeId) {

        return service.getChecksByEmployee(employeeId);
    }
}
