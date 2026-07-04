package com.kumaran.competitoranalysistool.dto;

public class AnalysisRequest {

    private String appOne;
    private String appTwo;
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