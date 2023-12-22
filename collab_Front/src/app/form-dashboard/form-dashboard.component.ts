import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-form-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatSelectModule,],
  templateUrl: './form-dashboard.component.html',
  styleUrl: './form-dashboard.component.css'
})
export class FormDashboardComponent implements OnInit {

  private currentDashboardId = localStorage.getItem('dashboardId');

  allMembers: any[] = [];

  taskForm: FormGroup;

  constructor(private apiService: ApiService, private router: Router) {

    this.taskForm = new FormGroup({
      name: new FormControl(''),
      members: new FormControl('')
    })

  }

  ngOnInit(): void {

    this.apiService.getAllMember().subscribe(
      data => {
        //this.taskForm.get('dashboard')?.setValue(data);
        data.forEach((element: any) => {
          this.allMembers.push(element);
        });
      }
    );

  }

  setSelectUser(members: any[]) {
    const allDetailsUsers: any[] = []
    this.allMembers.forEach((users: any) => {
      members.forEach((usersId: any) => {
        if (usersId != null && users.id == usersId) {
          allDetailsUsers.push(users);
        }
      });

    });

    this.taskForm.get('members')?.setValue(allDetailsUsers);
  }

  addDashboard() {
    const listUserSelect: any[] = this.taskForm.get('members')?.value
    //let userId = parseInt(this.taskForm.get('members')?.value);

    this.setSelectUser(listUserSelect);

    const formData = this.taskForm.value;

    console.log('form data', formData);

    this.apiService.addDashboard(formData).subscribe(
      (response) => {
        console.log('Dashboard add successful:', response);
         this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.error('Dashboard add failed:', error);
      }
    );
  }
}
