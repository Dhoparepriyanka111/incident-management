import { Component } from '@angular/core';
interface Incident {
  id: number;
  title: string;
  category: string;
  priority: string;
  status: string;
  assignedTo: string;
  createdAt: string;
}
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
 totalIncidents = 12;
  newIncidents = 5;
  inProgressIncidents = 4;
  resolvedIncidents = 3;

  incidents: Incident[] = [
    {
      id: 1,
      title: 'Login page not working',
      category: 'Application',
      priority: 'HIGH',
      status: 'NEW',
      assignedTo: 'Support Team',
      createdAt: '23 Nov 2026'
    },
    {
      id: 2,
      title: 'Email server issue',
      category: 'Network',
      priority: 'MEDIUM',
      status: 'IN_PROGRESS',
      assignedTo: 'IT Team',
      createdAt: '22 Nov 2026'
    },
    {
      id: 3,
      title: 'Printer connection issue',
      category: 'Hardware',
      priority: 'LOW',
      status: 'RESOLVED',
      assignedTo: 'Hardware Team',
      createdAt: '21 Nov 2026'
    },
    {
      id: 4,
      title: 'Database connection failure',
      category: 'Database',
      priority: 'HIGH',
      status: 'IN_PROGRESS',
      assignedTo: 'Development Team',
      createdAt: '20 Nov 2026'
    }
  ];
}
