package com.kumaran.competitoranalysistool.controller;

import com.kumaran.competitoranalysistool.dto.AnalysisRequest;
import com.kumaran.competitoranalysistool.dto.AnalysisResponse;
import com.kumaran.competitoranalysistool.service.AnalysisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/generate")
    public AnalysisResponse generateAnalysis(@Valid @RequestBody AnalysisRequest request) {
        return analysisService.generateAnalysis(request);
    }

    @GetMapping
    public List<AnalysisResponse> getAllReports() {
        return analysisService.getAllReports();
    }

    @GetMapping("/search")
    public List<AnalysisResponse> searchReports(@RequestParam String keyword) {
        return analysisService.searchReports(keyword);
    }

    @GetMapping("/latest")
    public List<AnalysisResponse> getLatestReports() {
        return analysisService.getLatestReports();
    }

    @GetMapping("/{id}")
    public AnalysisResponse getReportById(@PathVariable Long id) {
        return analysisService.getReportById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteReportById(@PathVariable Long id) {
        return analysisService.deleteReportById(id);
    }
}