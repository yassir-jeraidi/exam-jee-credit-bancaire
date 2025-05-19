import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {CreditsListComponent} from './shared/components/credits/credits-list/credits-list.component';
import {ClientListComponent} from './shared/components/clients/client-list/client-list.component';
import {
  RemboursementsListComponent
} from './shared/components/remboursements/remboursements-list/remboursements-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'credit-management';
}
