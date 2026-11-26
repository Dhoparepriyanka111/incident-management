
export interface Incident {
  id?: number;
  title: string;
  description: string;
  category: string;
  priority: string;
  status: string;
  reporterName: string;
  reporterEmail: string;
  assignedTo: string;
  createdAt?: string;
  updatedAt?: string;
}
