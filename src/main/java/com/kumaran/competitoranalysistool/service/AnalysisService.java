package com.kumaran.competitoranalysistool.service;

import com.kumaran.competitoranalysistool.dto.AnalysisRequest;
import com.kumaran.competitoranalysistool.dto.AnalysisResponse;
import com.kumaran.competitoranalysistool.entity.AnalysisReport;
import com.kumaran.competitoranalysistool.entity.CompetitorAnalysis;
import com.kumaran.competitoranalysistool.repository.AnalysisReportRepository;
import com.kumaran.competitoranalysistool.repository.CompetitorAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService {

    private final CompetitorAnalysisRepository competitorAnalysisRepository;
    private final AnalysisReportRepository analysisReportRepository;

    public AnalysisService(CompetitorAnalysisRepository competitorAnalysisRepository,
                           AnalysisReportRepository analysisReportRepository) {
        this.competitorAnalysisRepository = competitorAnalysisRepository;
        this.analysisReportRepository = analysisReportRepository;
    }

    public AnalysisResponse generateAnalysis(AnalysisRequest request) {

        CompetitorAnalysis analysis = new CompetitorAnalysis();
        analysis.setAppOne(request.getAppOne());
        analysis.setAppTwo(request.getAppTwo());
        analysis.setIndustry(request.getIndustry());

        CompetitorAnalysis savedAnalysis = competitorAnalysisRepository.save(analysis);

        AnalysisReport report = new AnalysisReport();

        report.setCoreFeatures(
                request.getAppOne() + " has strong food delivery, offers, and quick commerce support. " +
                        request.getAppTwo() + " has strong restaurant discovery, ratings, reviews, and food delivery experience."
        );

        report.setUxDesign(
                request.getAppOne() + " focuses on fast ordering, offers, and convenience. " +
                        request.getAppTwo() + " provides clean restaurant discovery, rating-based browsing, and smooth reorder flow."
        );

        report.setTargetUser(
                request.getAppOne() + " targets users who want food delivery, grocery delivery, and quick convenience. " +
                        request.getAppTwo() + " targets users who want restaurant discovery, food ordering, dining options, and trusted reviews."
        );

        report.setBusinessModel(
                "Both apps make money through restaurant commissions, delivery fees, platform charges, ads, subscriptions, and partner promotions."
        );

        report.setGaps(
                request.getAppOne() + " can improve personalized restaurant discovery and repeat-order experience. " +
                        request.getAppTwo() + " can improve deeper grocery integration and food-plus-grocery bundle experiences."
        );

        report.setOpportunity(
                "The best opportunity is to build a smart personalized reorder and bundle recommendation feature."
        );

        report.setStrategicRecommendation(
                "The next big product bet should be personalized repeat ordering. " +
                        "Many users order similar meals and essentials repeatedly. " +
                        "By combining order history, grocery needs, offers, delivery speed, and preferred restaurants, " +
                        "the app can improve user retention and increase order frequency."
        );

        report.setWinner("Tie");
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
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));

        return convertToResponse(report);
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