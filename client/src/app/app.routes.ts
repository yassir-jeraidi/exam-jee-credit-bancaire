import { Routes } from '@angular/router';
import { LoginComponent } from './features/components/login/login.component';
import { roleGuard } from './core/guards/role.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'admin',
    loadComponent: () =>
      import('./features/components/dashboard/admin/admin.component').then(
        (m) => m.AdminComponent
      ),
    canActivate: [roleGuard],
    data: { roles: ['ADMIN'] },
  },
  {
    path: 'client',
    loadComponent: () =>
      import('./features/components/dashboard/client/client.component').then(
        (m) => m.ClientComponent
      ),
    canActivate: [roleGuard],
    data: { roles: ['CLIENT'] },
  },
  {
    path: 'employe',
    loadComponent: () =>
      import('./features/components/dashboard/employe/employe.component').then(
        (m) => m.EmployeComponent
      ),
    canActivate: [roleGuard],
    data: { roles: ['EMPLOYE'] },
  },
];
