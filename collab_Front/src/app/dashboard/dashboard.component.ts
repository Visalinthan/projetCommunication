import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
  CdkDrag,
  CdkDropList,
} from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { CdkAccordionModule } from '@angular/cdk/accordion';
import { Router } from '@angular/router';
import { TaskStatus } from '../model/TaskStatus';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, CdkDropList, CdkDrag, MatTabsModule, CdkAccordionModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  dashboard: any[] = [];

  todo: any[] = [];

  progress: any[] = [];

  done: any[] = [];

  private currentUserId = localStorage.getItem('userId');

  private currentDashboardId = localStorage.getItem('dashboardId')

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {

    this.apiService.getDashboardByUser(this.currentUserId).subscribe(
      data => data.forEach((element: any) => {
        if (element != null) {
          localStorage.setItem('dashboardId', element.id);
        }

        this.dashboard.push(element);
      })
    );

    this.apiService.getTasksByDashboard(this.currentDashboardId).subscribe(data =>
      data.forEach((el: any) => {

        if (el.status == "PENDING") {
          this.todo = []
          this.todo.push(el)
        } else if (el.status == "IN_PROGRESS") {
          this.progress = []
          this.progress.push(el)
        } else if (el.status == "COMPLETED") {
          this.done = []
          this.done.push(el)
        }
      })
    );

  }

  changeDashboard(id: any) {
    alert(id);
    localStorage.setItem('dashboardId', id);
  }

  addTaskForm(): void {
    this.router.navigate(['/addTask']);
  }

  addDashboardForm(): void {
    this.router.navigate(['/addDashboard']);
  }

  updateStatus(taskId: any, columnId: any) {

    if (columnId == "cdk-drop-list-0") {
      this.apiService.updateStatusTask(taskId, TaskStatus.PENDING).subscribe((res) => {
        console.log(res)
      })
    } else if (columnId == "cdk-drop-list-1") {
      this.apiService.updateStatusTask(taskId, TaskStatus.IN_PROGRESS).subscribe((res) => {
        console.log(res)
      })
    } else if (columnId == "cdk-drop-list-2") {
      this.apiService.updateStatusTask(taskId, TaskStatus.COMPLETED).subscribe((res) => {
        console.log(res)
      })
    }

  }


  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );

      event.container.data.forEach((element: any) => {
        const taskId = element.id;

        this.updateStatus(taskId, event.container.id);
      });

    }

  }

}
