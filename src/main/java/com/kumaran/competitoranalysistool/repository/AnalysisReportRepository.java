package com.kumaran.competitoranalysistool.repository;

import com.kumaran.competitoranalysistool.entity.AnalysisReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisReportRepository extends JpaRepository<AnalysisReport, Long> {
}