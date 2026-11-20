
package com.example.incident_management.controller;

import com.example.incident_management.entity.Incident;
import com.example.incident_management.service.IncidentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Create incident
    @PostMapping
    public Incident createIncident(@Valid @RequestBody Incident incident) {
        return incidentService.createIncident(incident);
    }

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // Get incident by ID
    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id);
    }

    // Update incident
    @PutMapping("/{id}")
    public Incident updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody Incident incident) {

        return incidentService.updateIncident(id, incident);
    }

    // Delete incident
    @DeleteMapping("/{id}")
    public String deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return "Incident deleted successfully";
    }

    // Filter incidents by status
    @GetMapping("/status/{status}")
    public List<Incident> getIncidentsByStatus(
            @PathVariable String status) {

        return incidentService.getIncidentsByStatus(status);
    }

    // Filter incidents by priority
    @GetMapping("/priority/{priority}")
    public List<Incident> getIncidentsByPriority(
            @PathVariable String priority) {

        return incidentService.getIncidentsByPriority(priority);
    }

    // Filter incidents by category
    @GetMapping("/category/{category}")
    public List<Incident> getIncidentsByCategory(
            @PathVariable String category) {

        return incidentService.getIncidentsByCategory(category);
    }

    // Filter incidents by status and priority
    @GetMapping("/filter")
    public List<Incident> getIncidentsByStatusAndPriority(
            @RequestParam String status,
            @RequestParam String priority) {

        return incidentService.getIncidentsByStatusAndPriority(
                status, priority
        );
    }
    // Search incidents by title
@GetMapping("/search")
public List<Incident> searchIncidentsByTitle(
        @RequestParam String keyword) {

    return incidentService.searchIncidentsByTitle(keyword);
}

// Get incidents with pagination
@GetMapping("/page")
public Page<Incident> getIncidentsWithPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    return incidentService.getAllIncidents(
            PageRequest.of(page, size)
    );
}

@GetMapping("/sort")
public List<Incident> getSortedIncidents(
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {

    // Allow only valid Incident fields
    List<String> allowedFields = List.of(
            "id", "title", "priority", "status", "createdAt", "updatedAt"
    );

    if (!allowedFields.contains(sortBy)) {
        throw new IllegalArgumentException("Invalid sorting field: " + sortBy);
    }

    Sort sort;

    if (direction.equalsIgnoreCase("asc")) {
        sort = Sort.by(sortBy).ascending();
    } else if (direction.equalsIgnoreCase("desc")) {
        sort = Sort.by(sortBy).descending();
    } else {
        throw new IllegalArgumentException("Direction must be asc or desc");
    }

    return incidentService.getSortedIncidents(sort);
}

}
