package com.kumaran.competitoranalysistool.dto;

import jakarta.validation.constraints.NotBlank;

public class AnalysisRequest {

    @NotBlank(message = "App One is required")
    private String appOne;

    @NotBlank(message = "App Two is required")
    private String appTwo;

    @NotBlank(message = "Industry is required")
    private String industry;

    public AnalysisRequest() {
    }

    public String getAppOne() {
        return appOne;
    }

    public void setAppOne(String appOne) {
        this.appOne = appOne;
    }

    public String getAppTwo() {
        return appTwo;
    }

    public void setAppTwo(String appTwo) {
        this.appTwo = appTwo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}