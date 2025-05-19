import { Component } from '@angular/core';
import {NavbarComponent} from '../../../../shared/components/navbar/navbar.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-client',
  imports: [
    NavbarComponent,
    RouterOutlet
  ],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent {

}
