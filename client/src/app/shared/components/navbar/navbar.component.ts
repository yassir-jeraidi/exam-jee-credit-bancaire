import {Component, OnInit} from '@angular/core';
import {NgIf} from '@angular/common';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {AuthService} from '../../../core/services/auth.service';

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

  constructor(public authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.authService.me().subscribe({
      next: (user) => {
        const role = user?.role;
        this.isAdminOrEmploye = role === 'ADMIN' || role === 'EMPLOYE';
        this.isAdminOrEmployeOrClient = role === 'ADMIN' || role === 'EMPLOYE' || role === 'CLIENT';
      },
      error: (error) => {
        console.error('Error fetching user role', error);
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
    this.authService.logout().subscribe({
      next: () => {
        console.log('Logout successful');
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Logout failed', error);
      }
    });
  }
}
