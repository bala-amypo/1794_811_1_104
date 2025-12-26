package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_rules")
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String appliesToRole;
    private Integer maxDevicesAllowed;
    private Boolean active = true;

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public String getAppliesToRole() { return appliesToRole; }
    public Integer getMaxDevicesAllowed() { return maxDevicesAllowed; }
    public Boolean getActive() { return active; }

    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setAppliesToRole(String appliesToRole) { this.appliesToRole = appliesToRole; }
    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
    }
    public void setActive(Boolean active) { this.active = active; }
}
