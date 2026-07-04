package com.kumaran.competitoranalysistool.repository;

import com.kumaran.competitoranalysistool.entity.AnalysisReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalysisReportRepository extends JpaRepository<AnalysisReport, Long> {

    @Query("""
            SELECT report FROM AnalysisReport report
            JOIN report.competitorAnalysis analysis
            WHERE LOWER(analysis.appOne) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(analysis.appTwo) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(analysis.industry) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<AnalysisReport> searchReports(@Param("keyword") String keyword);

    List<AnalysisReport> findTop5ByOrderByCreatedAtDesc();
}