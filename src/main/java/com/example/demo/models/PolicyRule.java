package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "policy_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleCode")
    }
)
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;

    private String description;

    private String appliesToRole;

    private String appliesToDepartment;

    private Integer maxDevicesAllowed;

    private Boolean active = true;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppliesToRole() {
        return appliesToRole;
    }

    public void setAppliesToRole(String appliesToRole) {
        this.appliesToRole = appliesToRole;
    }

    public String getAppliesToDepartment() {
        return appliesToDepartment;
    }

    public void setAppliesToDepartment(String appliesToDepartment) {
        this.appliesToDepartment = appliesToDepartment;
    }

    public Integer getMaxDevicesAllowed() {
        return maxDevicesAllowed;
    }

    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // ================= ADDED METHODS (FIX COMPILATION) =================

    /**
     * Some services were calling getName().
     * We map it safely to ruleCode.
     */
    public String getName() {
        return this.ruleCode;
    }

    /**
     * Eligibility helper used by EligibilityCheckService.
     * Simple safe logic for now (can be expanded later).
     */
    public boolean isEligible(EmployeeProfile employee, DeviceCatalogItem device) {

        if (Boolean.FALSE.equals(this.active)) {
            return true; // inactive rules are ignored
        }

        if (appliesToRole != null &&
                !appliesToRole.equalsIgnoreCase(employee.getJobRole())) {
            return true; // rule does not apply
        }

        if (appliesToDepartment != null &&
                !appliesToDepartment.equalsIgnoreCase(employee.getDepartment())) {
            return true; // rule does not apply
        }

        // maxDevicesAllowed check happens in service layer
        return true;
    }
}
