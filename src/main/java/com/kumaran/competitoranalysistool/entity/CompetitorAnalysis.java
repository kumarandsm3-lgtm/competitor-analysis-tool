package com.kumaran.competitoranalysistool.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "competitor_analysis")
public class CompetitorAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appOne;

    private String appTwo;

    private String industry;

    private LocalDateTime createdAt;

    public CompetitorAnalysis() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}