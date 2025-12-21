package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode"))
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

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public Integer getMaxDevicesAllowed() { return maxDevicesAllowed; }
    public Boolean getActive() { return active; }
}
