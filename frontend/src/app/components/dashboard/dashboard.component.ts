
import { Component, OnInit } from '@angular/core';
import { Incident } from '../../models/incident.model';
import { DashboardSummary } from '../../models/dashboard-summary.model';
import { IncidentService } from '../../services/incident.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ CommonModule,
    RouterLink,
    RouterLinkActive],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  totalIncidents = 0;
  newIncidents = 0;
  inProgressIncidents = 0;
  resolvedIncidents = 0;

  highPriorityIncidents = 0;
  mediumPriorityIncidents = 0;
  lowPriorityIncidents = 0;

  incidents: Incident[] = [];
  errorMessage = '';

  constructor(private incidentService: IncidentService) {}

  ngOnInit(): void {
    this.loadIncidents();
    this.loadDashboardSummary();
  }

  loadIncidents(): void {
    this.incidentService.getAllIncidents().subscribe({
      next: (data) => {
        this.incidents = data;
        console.log('Incidents loaded:', data);
      },
      error: (error) => {
        console.error('Error loading incidents:', error);
        this.errorMessage = 'Unable to load incidents.';
      }
    });
  }

  loadDashboardSummary(): void {
    this.incidentService.getDashboardSummary().subscribe({
      next: (data: DashboardSummary) => {
        this.totalIncidents = data.totalIncidents;
        this.newIncidents = data.newIncidents;
        this.inProgressIncidents = data.inProgressIncidents;
        this.resolvedIncidents = data.resolvedIncidents;

        this.highPriorityIncidents = data.highPriorityIncidents;
        this.mediumPriorityIncidents = data.mediumPriorityIncidents;
        this.lowPriorityIncidents = data.lowPriorityIncidents;

        console.log('Dashboard summary loaded:', data);
      },
      error: (error) => {
        console.error('Error loading dashboard summary:', error);
      }
    });
  }
}
