import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormTaskComponent } from './form-task/form-task.component';
import { FormDashboardComponent } from './form-dashboard/form-dashboard.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  { path: 'regiter', component: RegisterComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'addDashboard', component: FormDashboardComponent },
  { path: 'addTask', component: FormTaskComponent },

];
