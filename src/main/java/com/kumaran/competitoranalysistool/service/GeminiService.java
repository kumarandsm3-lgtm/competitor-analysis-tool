package com.kumaran.competitoranalysistool.service;

import com.kumaran.competitoranalysistool.dto.AnalysisRequest;
import com.kumaran.competitoranalysistool.dto.AnalysisResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public GeminiService() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
    }

    public AnalysisResponse generateCompetitorReport(AnalysisRequest request) {

        try {
            String prompt = buildPrompt(request);

            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of(
                                    "parts", List.of(
                                            Map.of("text", prompt)
                                    )
                            )
                    ),
                    "generationConfig", Map.of(
                            "responseMimeType", "application/json"
                    )
            );

            String jsonBody = objectMapper.writeValueAsString(requestBody);

            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/"
                    + model + ":generateContent";

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .header("x-goog-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (httpResponse.statusCode() < 200 || httpResponse.statusCode() > 299) {
                throw new RuntimeException("Gemini API error: " + httpResponse.body());
            }

            JsonNode root = objectMapper.readTree(httpResponse.body());

            String aiJson = root
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText();

            if (aiJson == null || aiJson.isBlank()) {
                throw new RuntimeException("Gemini returned empty response");
            }

            JsonNode reportNode = objectMapper.readTree(aiJson);

            AnalysisResponse response = new AnalysisResponse();
            response.setWinner(reportNode.path("winner").asText());
            response.setCoreFeatures(reportNode.path("coreFeatures").asText());
            response.setUxDesign(reportNode.path("uxDesign").asText());
            response.setTargetUser(reportNode.path("targetUser").asText());
            response.setBusinessModel(reportNode.path("businessModel").asText());
            response.setGaps(reportNode.path("gaps").asText());
            response.setOpportunity(reportNode.path("opportunity").asText());
            response.setStrategicRecommendation(reportNode.path("strategicRecommendation").asText());

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Gemini report: " + e.getMessage());
        }
    }

    private String buildPrompt(AnalysisRequest request) {

        return """
                You are a senior product manager doing a competitive analysis.

                Compare the following two apps.

                App 1: %s
                App 2: %s
                Industry: %s

                Compare across these dimensions:

                1. Core features — what does each app have that the other does not?
                2. UX and design — where is each app stronger or weaker?
                3. Target user — who is each app primarily built for?
                4. Business model — how does each app make money?
                5. Gaps — where is App 1 losing users to App 2?
                6. Opportunity — if you were the PM at App 1, what one feature would you build to win back users?
                7. Strategic recommendation — give a practical product roadmap suggestion.

                Return only valid JSON.
                Do not include markdown.
                Do not include explanation outside JSON.

                JSON format:
                {
                  "winner": "",
                  "coreFeatures": "",
                  "uxDesign": "",
                  "targetUser": "",
                  "businessModel": "",
                  "gaps": "",
                  "opportunity": "",
                  "strategicRecommendation": ""
                }
                """.formatted(
                request.getAppOne(),
                request.getAppTwo(),
                request.getIndustry()
        );
    }
}