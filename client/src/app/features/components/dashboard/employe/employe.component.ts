import { Component } from '@angular/core';
import {NavbarComponent} from "../../../../shared/components/navbar/navbar.component";
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-employe',
  imports: [
    NavbarComponent,
    RouterOutlet
  ],
  templateUrl: './employe.component.html',
  styleUrl: './employe.component.css'
})
export class EmployeComponent {

}
