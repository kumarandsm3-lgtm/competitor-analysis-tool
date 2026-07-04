package com.kumaran.competitoranalysistool.controller;

import com.kumaran.competitoranalysistool.dto.AnalysisRequest;
import com.kumaran.competitoranalysistool.dto.AnalysisResponse;
import com.kumaran.competitoranalysistool.service.AnalysisService;
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
    public AnalysisResponse generateAnalysis(@RequestBody AnalysisRequest request) {
        return analysisService.generateAnalysis(request);
    }

    @GetMapping
    public List<AnalysisResponse> getAllReports() {
        return analysisService.getAllReports();
    }

    @GetMapping("/{id}")
    public AnalysisResponse getReportById(@PathVariable Long id) {
        return analysisService.getReportById(id);
    }
}