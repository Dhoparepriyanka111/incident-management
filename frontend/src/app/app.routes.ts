import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AllIncidentsComponent } from './components/all-incidents/all-incidents.component';

export const routes: Routes = [
     {
    path: '',
    component: DashboardComponent
  },

  {
    path: 'incidents',
    component: AllIncidentsComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];
