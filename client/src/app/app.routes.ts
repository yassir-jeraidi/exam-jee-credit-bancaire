import {Routes} from '@angular/router';
import {LoginComponent} from './features/components/login/login.component';
import {roleGuard} from './core/guards/role.guard';
import {ClientListComponent} from './shared/components/clients/client-list/client-list.component';
import {CreditsListComponent} from './shared/components/credits/credits-list/credits-list.component';
import {
  RemboursementsListComponent
} from './shared/components/remboursements/remboursements-list/remboursements-list.component';


export const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},


  {
    path: 'admin',
    canActivate: [roleGuard],
    data: {roles: ['ADMIN']},
    loadComponent: () =>
      import('./features/components/dashboard/admin/admin.component').then(
        (m) => m.AdminComponent
      ),
    children: [
      {path: '', redirectTo: 'clients', pathMatch: 'full'},
      {path: 'clients', component: ClientListComponent},
      {path: 'credits', component: CreditsListComponent},
      {path: 'remboursements', component: RemboursementsListComponent},
    ],
  },


  {
    path: 'client',
    canActivate: [roleGuard],
    data: {roles: ['CLIENT']},
    loadComponent: () =>
      import('./features/components/dashboard/client/client.component').then(
        (m) => m.ClientComponent
      ),
    children: [
      {path: '', redirectTo: 'credits', pathMatch: 'full'},
      {path: 'clients', component: ClientListComponent},
      {path: 'credits', component: CreditsListComponent},
      {path: 'remboursements', component: RemboursementsListComponent},
    ],
  },


  {
    path: 'employe',
    canActivate: [roleGuard],
    data: {roles: ['EMPLOYE']},
    loadComponent: () =>
      import('./features/components/dashboard/employe/employe.component').then(
        (m) => m.EmployeComponent
      ),
    children: [
      {path: '', redirectTo: 'credits', pathMatch: 'full'},
      {path: 'credits', component: CreditsListComponent},
      {path: 'remboursements', component: RemboursementsListComponent},
    ],
  },


  {path: '**', redirectTo: '/login'},
];
