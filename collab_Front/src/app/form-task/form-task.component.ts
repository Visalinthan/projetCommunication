import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-task',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './form-task.component.html',
  styleUrl: './form-task.component.css'
})
export class FormTaskComponent implements OnInit {

  private currentDashboardId = localStorage.getItem('dashboardId');

  members: any[] = [];

  taskStatus = ['PENDING', 'IN_PROGRESS', 'COMPLETED'];

  taskForm: FormGroup;

  constructor(private apiService: ApiService, private router: Router) {

    const dateNow = new Date();

    this.taskForm = new FormGroup({
      title: new FormControl(''),
      description: new FormControl(''),
      dateCreation: new FormControl(dateNow.toLocaleDateString()),
      dateLimit: new FormControl(''),
      priority: new FormControl(''),
      status: new FormControl(''),
      dashboard: new FormControl(''),
      user: new FormControl('')
    })

  }

  ngOnInit(): void {

    this.apiService.getDashboardById(this.currentDashboardId).subscribe(
      data => {
        this.taskForm.get('dashboard')?.setValue(data);
        data.members.forEach((element: any) => {
          this.members.push(element);
        });
      }
    );

  }

  setSelectUser(userId:number) {
    this.members.forEach((element: any) => {
      if (userId != null && element.id == userId) {
        this.taskForm.get('user')?.setValue(element);
      }
    });
  }

  addTask() {

    let userId = parseInt(this.taskForm.get('user')?.value);
    this.setSelectUser(userId);

    const formData = this.taskForm.value;

    console.log('form data', formData);

    this.apiService.addTask(formData).subscribe(
      (response) => {
        console.log('Task add successful:', response);
         this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.error('Task add failed:', error);
      }
    );
  }

}
