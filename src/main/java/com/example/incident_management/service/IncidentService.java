
package com.example.incident_management.service;

import com.example.incident_management.entity.Incident;
import com.example.incident_management.repository.IncidentRepository;
import com.example.incident_management.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    // Create incident
    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    // Get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Get incident by ID
    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Incident not found with id: " + id
                        )
                );
    }

    // Update incident
    public Incident updateIncident(Long id, Incident updatedIncident) {

        Incident existingIncident = getIncidentById(id);

        existingIncident.setTitle(updatedIncident.getTitle());
        existingIncident.setDescription(updatedIncident.getDescription());
        existingIncident.setCategory(updatedIncident.getCategory());
        existingIncident.setPriority(updatedIncident.getPriority());
        existingIncident.setStatus(updatedIncident.getStatus());
        existingIncident.setReporterName(updatedIncident.getReporterName());
        existingIncident.setReporterEmail(updatedIncident.getReporterEmail());
        existingIncident.setAssignedTo(updatedIncident.getAssignedTo());

        return incidentRepository.save(existingIncident);
    }

    // Delete incident
    public void deleteIncident(Long id) {
        Incident existingIncident = getIncidentById(id);
        incidentRepository.delete(existingIncident);
    }

    // Filter incidents by status
    public List<Incident> getIncidentsByStatus(String status) {
        return incidentRepository.findByStatus(status);
    }

    // Filter incidents by priority
    public List<Incident> getIncidentsByPriority(String priority) {
        return incidentRepository.findByPriority(priority);
    }

    // Filter incidents by category
    public List<Incident> getIncidentsByCategory(String category) {
        return incidentRepository.findByCategory(category);
    }

    // Filter incidents by status and priority
    public List<Incident> getIncidentsByStatusAndPriority(
            String status, String priority) {

        return incidentRepository.findByStatusAndPriority(status, priority);
    }
    // Search incidents by title
public List<Incident> searchIncidentsByTitle(String keyword) {
    return incidentRepository.searchByTitle(keyword);
}
// Get incidents with pagination
public Page<Incident> getAllIncidents(Pageable pageable) {
    return incidentRepository.findAll(pageable);
}
// Get incidents with sorting
public List<Incident> getSortedIncidents(Sort sort) {
    return incidentRepository.findAll(sort);
}
}
