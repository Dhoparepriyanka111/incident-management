package com.example.incident_management.service;


import com.example.incident_management.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IncidentDashboardService {

    private final IncidentRepository incidentRepository;

    public IncidentDashboardService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public long getTotalIncidents() {
        return incidentRepository.count();
    }

    public long getNewIncidents() {
        return incidentRepository.countByStatus("NEW");
    }

    public long getInProgressIncidents() {
        return incidentRepository.countByStatus("IN_PROGRESS");
    }

    public long getResolvedIncidents() {
        return incidentRepository.countByStatusIn(
                Arrays.asList("RESOLVED", "CLOSED")
        );
    }

    public long getHighPriorityIncidents() {
        return incidentRepository.countByPriority("HIGH");
    }
    public long getLowPriorityIncidents() {
    return incidentRepository.countByPriority("LOW");
}

public long getMediumPriorityIncidents() {
    return incidentRepository.countByPriority("MEDIUM");
}
}

