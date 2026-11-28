
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink} from '@angular/router';

import { IncidentService } from '../../services/incident.service';
import { Incident } from '../../models/incident.model';

@Component({
  selector: 'app-incident-details',
  standalone: true,
imports: [CommonModule, RouterLink],
  templateUrl: './incident-details.component.html',
  styleUrl: './incident-details.component.css'
})
export class IncidentDetailsComponent implements OnInit {

  incident: Incident | null = null;

  isLoading = false;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private incidentService: IncidentService
  ) {}

  ngOnInit(): void {

    // Read incident ID from URL
    this.route.paramMap.subscribe(params => {

      const id = Number(params.get('id'));

      if (!Number.isInteger(id) || id <= 0) {

        this.errorMessage = 'Invalid incident ID.';
        this.isLoading = false;
        return;

      }

      this.loadIncident(id);

    });

  }

  // Fetch incident details from backend
  loadIncident(id: number): void {

    this.isLoading = true;
    this.errorMessage = '';

    this.incidentService.getIncidentById(id).subscribe({

      next: (data) => {

        this.incident = data;
        this.isLoading = false;

        console.log('Incident details loaded:', data);

      },

      error: (error) => {

        console.error('Error loading incident details:', error);

        this.errorMessage =
          'Unable to load incident details. Please try again.';

        this.isLoading = false;

      }

    });

  }

}
