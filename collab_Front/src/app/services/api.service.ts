import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'api';

  constructor(private http: HttpClient) { }

  addMember(member: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/members`, member)
  }

  getMemberById(userId: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/members/${userId}`).pipe(
      map((response) => response)
    );
  }

  getAllMember(): Observable<any> {
    return this.http.get(`${this.apiUrl}/members`).pipe(
      map((response) => response)
    );
  }

  addDashboard(dashboard: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/dashboards`, dashboard)
  }

  getDashboardByUser(userId: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboards/user/${userId}`).pipe(
      map((response) => response)
    );
  }

  getDashboardById(dashboardId: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboards/${dashboardId}`).pipe(
      map((response) => response)
    );
  }

  addTask(task: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/tasks`, task);
  }

  getTasksByDashboard(dashboardId: any): Observable<any> {
    return this.http.get(`${this.apiUrl}/tasks/dashboard/${dashboardId}`).pipe(
      map((response) => response)
    );
  }

  updateStatusTask(taskId: any, status: string): Observable<any> {
    //alert(status)
    return this.http.post(`${this.apiUrl}/tasks/status/${taskId}`, status);
  }
}
