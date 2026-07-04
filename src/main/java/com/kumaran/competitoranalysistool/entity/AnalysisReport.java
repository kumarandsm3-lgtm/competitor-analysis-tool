package com.kumaran.competitoranalysistool.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_report")
public class AnalysisReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String winner;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String coreFeatures;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String uxDesign;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String targetUser;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String businessModel;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String gaps;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String opportunity;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String strategicRecommendation;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "analysis_id")
    private CompetitorAnalysis competitorAnalysis;

    public AnalysisReport() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CompetitorAnalysis getCompetitorAnalysis() {
        return competitorAnalysis;
    }

    public void setCompetitorAnalysis(CompetitorAnalysis competitorAnalysis) {
        this.competitorAnalysis = competitorAnalysis;
    }
}