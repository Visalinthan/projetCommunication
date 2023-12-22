import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: '../app.component.css',
  imports: [CommonModule,FormsModule],
  providers:[LoginService]
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  login() {
    const loginDto = { username: this.username, password: this.password };

    this.loginService.loginUser(loginDto).subscribe(
      (response) => {
        console.log('Login successful:', response);
        // Handle successful login, e.g., navigate to another page
        localStorage.setItem('userId', response.id);
        this.router.navigate(['dashboard']);
      },
      (error) => {
        console.error('Login failed:', error);
        // Handle login failure, e.g., display an error message
      }
    )

  }

}
