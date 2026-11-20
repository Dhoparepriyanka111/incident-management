
package com.example.incident_management.repository;

import com.example.incident_management.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // Find incidents by status
    List<Incident> findByStatus(String status);

    // Find incidents by priority
    List<Incident> findByPriority(String priority);

    // Find incidents by category
    List<Incident> findByCategory(String category);

    // Find incidents by status and priority
    List<Incident> findByStatusAndPriority(String status, String priority);

    // Search incidents by title
@Query("SELECT i FROM Incident i WHERE LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
List<Incident> searchByTitle(@Param("keyword") String keyword);
// Get incidents with pagination
Page<Incident> findAll(Pageable pageable);

long countByStatus(String status);

long countByPriority(String priority);

long countByStatusIn(List<String> statuses);

long countByPriorityIn(List<String> priorities);

}
