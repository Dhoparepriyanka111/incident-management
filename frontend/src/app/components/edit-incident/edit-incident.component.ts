
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { ActivatedRoute } from '@angular/router';
import { IncidentService } from '../../services/incident.service';
import { Incident } from '../../models/incident.model';

@Component({
  selector: 'app-edit-incident',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-incident.component.html',
  styleUrl: './edit-incident.component.css'
})
export class EditIncidentComponent implements OnInit {

  incidentForm: FormGroup;

  incidentId = 0;
  isLoading = false;
  isSubmitting = false;

  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private incidentService: IncidentService
  ) {

    this.incidentForm = this.fb.group({

      title: ['', Validators.required],

      description: ['', Validators.required],

      category: ['', Validators.required],

      priority: ['MEDIUM', Validators.required],

      status: ['NEW', Validators.required],

      reporterName: ['', Validators.required],

      reporterEmail: ['', [
        Validators.required,
        Validators.email
      ]],

      assignedTo: ['']

    });

  }

  ngOnInit(): void {

    // Read incident ID from URL
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (!Number.isInteger(id) || id <= 0) {
      this.errorMessage = 'Invalid incident ID.';
      return;
    }

    this.incidentId = id;

    this.loadIncident(id);

  }

  // Fetch existing incident details
  loadIncident(id: number): void {

    this.isLoading = true;
    this.errorMessage = '';

    this.incidentService.getIncidentById(id).subscribe({

      next: (data: Incident) => {

        // Populate form with existing incident data
        this.incidentForm.patchValue({

          title: data.title,
          description: data.description,
          category: data.category,
          priority: data.priority,
          status: data.status,
          reporterName: data.reporterName,
          reporterEmail: data.reporterEmail,
          assignedTo: data.assignedTo || ''

        });

        this.isLoading = false;

      },

      error: (error) => {

        console.error('Error loading incident:', error);

        this.errorMessage = 'Unable to load incident details.';
        this.isLoading = false;

      }

    });

  }

  // Update incident
  onSubmit(): void {

    this.successMessage = '';
    this.errorMessage = '';

    if (this.incidentForm.invalid) {

      this.incidentForm.markAllAsTouched();
      return;

    }

    this.isSubmitting = true;

    const updatedData: Partial<Incident> =
      this.incidentForm.getRawValue();

    this.incidentService.updateIncident(
      this.incidentId,
      updatedData
    ).subscribe({

      next: (response) => {

        console.log('Incident updated successfully:', response);

        this.isSubmitting = false;

        this.successMessage =
          `Incident #${response.id} updated successfully!`;

      },

      error: (error) => {

        console.error('Error updating incident:', error);

        this.isSubmitting = false;

        this.errorMessage =
          error.error?.message || 'Unable to update incident. Please try again.';

      }

    });

  }

}
