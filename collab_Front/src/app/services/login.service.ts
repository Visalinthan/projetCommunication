import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'api/login'; // Replace with your Spring Boot backend URL

  constructor(private http: HttpClient) {}

  loginUser(loginDto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, loginDto);
  }
}
