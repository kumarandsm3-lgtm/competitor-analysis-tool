package com.kumaran.competitoranalysistool.repository;

import com.kumaran.competitoranalysistool.entity.CompetitorAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorAnalysisRepository extends JpaRepository<CompetitorAnalysis, Long> {
}