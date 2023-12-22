import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  providers: [ApiService],
  templateUrl: './register.component.html',
  styleUrl: '../app.component.css'
})
export class RegisterComponent {
  lastName: string = '';
  firstName: string = '';
  username: string = '';
  email: string = '';
  password: string = '';

  memberForm: FormGroup;


  constructor(private apiService: ApiService, private router: Router) {

    this.memberForm = new FormGroup({
      lastName: new FormControl(''),
      firstName: new FormControl(''),
      email: new FormControl(''),
      username: new FormControl(''),
      password: new FormControl('')
    })

  }


  register() {

    const formData = this.memberForm.value;

    this.apiService.addMember(formData).subscribe(
      (response) => {
        console.log(response)
        this.router.navigate(['login']);
      },
      (error) => {
        console.error('Register failed:', error);
      }
    )
  }
}
