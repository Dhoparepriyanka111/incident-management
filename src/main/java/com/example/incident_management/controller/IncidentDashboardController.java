package com.example.incident_management.controller;

import com.example.incident_management.service.IncidentDashboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/incidents/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidentDashboardController {

    private final IncidentDashboardService dashboardService;

    public IncidentDashboardController(
            IncidentDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("totalIncidents",
                dashboardService.getTotalIncidents());

        summary.put("newIncidents",
                dashboardService.getNewIncidents());

        summary.put("inProgressIncidents",
                dashboardService.getInProgressIncidents());

        summary.put("resolvedIncidents",
                dashboardService.getResolvedIncidents());

        summary.put("highPriorityIncidents",
                dashboardService.getHighPriorityIncidents());
        summary.put("lowPriorityIncidents",
                dashboardService.getLowPriorityIncidents());

        summary.put("mediumPriorityIncidents",
                dashboardService.getMediumPriorityIncidents());

        return summary;

    }
}
