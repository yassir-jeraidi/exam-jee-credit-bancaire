import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NgIf, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  mobileMenuOpen = false;
  profileMenuOpen = false;
  isAdminOrEmploye = false;
  isAdminOrEmployeOrClient = false;
  isLoggedIn = false;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.me().subscribe({
      next: (user) => {
        this.isLoggedIn = !!user;
        const role = user?.role;
        this.isAdminOrEmploye = role === 'ADMIN' || role === 'EMPLOYE';
        this.isAdminOrEmployeOrClient = role === 'ADMIN' || role === 'EMPLOYE' || role === 'CLIENT';
      },
      error: (error) => {
        console.error('Error fetching user role', error);
        this.isLoggedIn = false;
        this.isAdminOrEmploye = false;
        this.isAdminOrEmployeOrClient = false;
      }
    });
  }

  toggleMobileMenu(): void {
    this.mobileMenuOpen = !this.mobileMenuOpen;
  }

  toggleProfileMenu(): void {
    this.profileMenuOpen = !this.profileMenuOpen;
  }

  logout(): void {
    this.isLoggedIn = false;
    this.isAdminOrEmploye = false;
    this.isAdminOrEmployeOrClient = false;
    this.router.navigate(['/login']);
  }
}
