package com.kumaran.competitoranalysistool.service;

import com.kumaran.competitoranalysistool.dto.AnalysisRequest;
import com.kumaran.competitoranalysistool.dto.AnalysisResponse;
import com.kumaran.competitoranalysistool.entity.AnalysisReport;
import com.kumaran.competitoranalysistool.entity.CompetitorAnalysis;
import com.kumaran.competitoranalysistool.exception.ResourceNotFoundException;
import com.kumaran.competitoranalysistool.repository.AnalysisReportRepository;
import com.kumaran.competitoranalysistool.repository.CompetitorAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService {

    private final CompetitorAnalysisRepository competitorAnalysisRepository;
    private final AnalysisReportRepository analysisReportRepository;
    private final GeminiService geminiService;

    public AnalysisService(CompetitorAnalysisRepository competitorAnalysisRepository,
                           AnalysisReportRepository analysisReportRepository,
                           GeminiService geminiService) {
        this.competitorAnalysisRepository = competitorAnalysisRepository;
        this.analysisReportRepository = analysisReportRepository;
        this.geminiService = geminiService;
    }

    public AnalysisResponse generateAnalysis(AnalysisRequest request) {

        CompetitorAnalysis analysis = new CompetitorAnalysis();
        analysis.setAppOne(request.getAppOne());
        analysis.setAppTwo(request.getAppTwo());
        analysis.setIndustry(request.getIndustry());

        CompetitorAnalysis savedAnalysis = competitorAnalysisRepository.save(analysis);

        AnalysisResponse aiResponse = geminiService.generateCompetitorReport(request);

        AnalysisReport report = new AnalysisReport();

        report.setWinner(aiResponse.getWinner());
        report.setCoreFeatures(aiResponse.getCoreFeatures());
        report.setUxDesign(aiResponse.getUxDesign());
        report.setTargetUser(aiResponse.getTargetUser());
        report.setBusinessModel(aiResponse.getBusinessModel());
        report.setGaps(aiResponse.getGaps());
        report.setOpportunity(aiResponse.getOpportunity());
        report.setStrategicRecommendation(aiResponse.getStrategicRecommendation());
        report.setCompetitorAnalysis(savedAnalysis);

        AnalysisReport savedReport = analysisReportRepository.save(report);

        return convertToResponse(savedReport);
    }

    public List<AnalysisResponse> getAllReports() {

        List<AnalysisReport> reports = analysisReportRepository.findAll();

        List<AnalysisResponse> responses = new ArrayList<>();

        for (AnalysisReport report : reports) {
            responses.add(convertToResponse(report));
        }

        return responses;
    }

    public AnalysisResponse getReportById(Long id) {

        AnalysisReport report = analysisReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));

        return convertToResponse(report);
    }

    public String deleteReportById(Long id) {

        AnalysisReport report = analysisReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));

        CompetitorAnalysis analysis = report.getCompetitorAnalysis();

        analysisReportRepository.delete(report);

        if (analysis != null) {
            competitorAnalysisRepository.delete(analysis);
        }

        return "Report deleted successfully with id: " + id;
    }

    private AnalysisResponse convertToResponse(AnalysisReport report) {

        CompetitorAnalysis analysis = report.getCompetitorAnalysis();

        AnalysisResponse response = new AnalysisResponse();

        response.setReportId(report.getId());
        response.setAppOne(analysis.getAppOne());
        response.setAppTwo(analysis.getAppTwo());
        response.setIndustry(analysis.getIndustry());
        response.setWinner(report.getWinner());
        response.setCoreFeatures(report.getCoreFeatures());
        response.setUxDesign(report.getUxDesign());
        response.setTargetUser(report.getTargetUser());
        response.setBusinessModel(report.getBusinessModel());
        response.setGaps(report.getGaps());
        response.setOpportunity(report.getOpportunity());
        response.setStrategicRecommendation(report.getStrategicRecommendation());

        return response;
    }
}