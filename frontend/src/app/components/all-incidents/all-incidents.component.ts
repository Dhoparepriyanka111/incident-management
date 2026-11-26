
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IncidentService } from '../../services/incident.service';
import { Incident } from '../../models/incident.model';

@Component({
  selector: 'app-all-incidents',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './all-incidents.component.html',
  styleUrl: './all-incidents.component.css'
})
export class AllIncidentsComponent implements OnInit {

  incidents: Incident[] = [];
  filteredIncidents: Incident[] = [];

  searchText: string = '';
  selectedStatus: string = '';
  selectedPriority: string = '';

  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private incidentService: IncidentService) {}

  ngOnInit(): void {
    this.loadIncidents();
  }

  // Load all incidents from backend
  loadIncidents(): void {

    this.isLoading = true;
    this.errorMessage = '';

    this.incidentService.getAllIncidents().subscribe({

      next: (data) => {
        this.incidents = data;
        this.filteredIncidents = data;
        this.isLoading = false;
      },

      error: (error) => {
        console.error('Error loading incidents:', error);
        this.errorMessage = 'Unable to load incidents. Please try again.';
        this.isLoading = false;
      }

    });
  }

  // Search and filter incidents
  filterIncidents(): void {

    this.filteredIncidents = this.incidents.filter((incident) => {

      const matchesSearch =
        incident.title.toLowerCase().includes(this.searchText.toLowerCase());

      const matchesStatus =
        !this.selectedStatus || incident.status === this.selectedStatus;

      const matchesPriority =
        !this.selectedPriority || incident.priority === this.selectedPriority;

      return matchesSearch && matchesStatus && matchesPriority;

    });

  }

  // Clear all filters
  clearFilters(): void {

    this.searchText = '';
    this.selectedStatus = '';
    this.selectedPriority = '';

    this.filteredIncidents = this.incidents;

  }

}
