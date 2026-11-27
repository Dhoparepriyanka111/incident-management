
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { IncidentService } from '../../services/incident.service';

@Component({
  selector: 'app-create-incident',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './create-incident.component.html',
  styleUrl: './create-incident.component.css'
})
export class CreateIncidentComponent {

  incidentForm: FormGroup;

  isSubmitting = false;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private incidentService: IncidentService
  ) {

    this.incidentForm = this.fb.group({

      title: ['', Validators.required],

      description: ['', Validators.required],

      category: ['', Validators.required],

      priority: ['MEDIUM', Validators.required],

      reporterName: ['', Validators.required],

      reporterEmail: ['', [
        Validators.required,
        Validators.email
      ]],

      assignedTo: ['']

    });

  }

  // Submit incident form
  onSubmit(): void {

    this.successMessage = '';
    this.errorMessage = '';

    // Check form validation
    if (this.incidentForm.invalid) {

      this.incidentForm.markAllAsTouched();
      return;

    }

    // Prepare incident data
    const incidentData = {
      ...this.incidentForm.value,
      status: 'NEW'
    };

    this.isSubmitting = true;

    // Call backend POST API
    this.incidentService.createIncident(incidentData).subscribe({

      next: (response) => {

        console.log('Incident created successfully:', response);

        this.isSubmitting = false;

        this.successMessage =
          `Incident #${response.id} created successfully!`;

        // Reset form after successful submission
        this.incidentForm.reset({
          title: '',
          description: '',
          category: '',
          priority: 'MEDIUM',
          reporterName: '',
          reporterEmail: '',
          assignedTo: ''
        });

      },

      error: (error) => {

        console.error('Error creating incident:', error);

        this.isSubmitting = false;

        this.errorMessage =
          error.error?.message || 'Unable to create incident. Please try again.';

      }

    });

  }

}
