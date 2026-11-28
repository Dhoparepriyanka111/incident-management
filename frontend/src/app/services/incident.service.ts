
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Incident } from '../models/incident.model';
import { DashboardSummary } from '../models/dashboard-summary.model';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {

  private apiUrl = 'http://localhost:8080/api/incidents';

  private dashboardUrl =
    'http://localhost:8080/api/incidents/dashboard/summary';

  constructor(private http: HttpClient) {}

  // Get all incidents
  getAllIncidents(): Observable<Incident[]> {
    return this.http.get<Incident[]>(this.apiUrl);
  }


  // Create new incident
  createIncident(incident: Partial<Incident>): Observable<Incident> {
    return this.http.post<Incident>(this.apiUrl, incident);
  }

  // Get dashboard summary
  getDashboardSummary(): Observable<DashboardSummary> {
    return this.http.get<DashboardSummary>(this.dashboardUrl);
  }

  // Get incident by ID
  getIncidentById(id: number): Observable<Incident> {
    return this.http.get<Incident>(`${this.apiUrl}/${id}`);
  }
// Update existing incident
updateIncident(
  id: number,
  incident: Partial<Incident>
): Observable<Incident> {
  return this.http.put<Incident>(
    `${this.apiUrl}/${id}`,
    incident
  );
}
}
