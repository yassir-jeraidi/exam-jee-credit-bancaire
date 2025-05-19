import { Component } from '@angular/core';
import {NavbarComponent} from '../../../../shared/components/navbar/navbar.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-admin',
  imports: [
    NavbarComponent,
    RouterOutlet
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

}
