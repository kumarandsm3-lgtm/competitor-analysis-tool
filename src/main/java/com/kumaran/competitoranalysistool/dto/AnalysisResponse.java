package com.kumaran.competitoranalysistool.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisResponse {

    private Long reportId;
    private String appOne;
    private String appTwo;
    private String industry;
    private String winner;
    private String coreFeatures;
    private String uxDesign;
    private String targetUser;
    private String businessModel;
    private String gaps;
    private String opportunity;
    private String strategicRecommendation;

    public AnalysisResponse() {
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getCoreFeatures() {
        return coreFeatures;
    }

    public void setCoreFeatures(String coreFeatures) {
        this.coreFeatures = coreFeatures;
    }

    public String getUxDesign() {
        return uxDesign;
    }

    public void setUxDesign(String uxDesign) {
        this.uxDesign = uxDesign;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public String getStrategicRecommendation() {
        return strategicRecommendation;
    }

    public void setStrategicRecommendation(String strategicRecommendation) {
        this.strategicRecommendation = strategicRecommendation;
    }
}