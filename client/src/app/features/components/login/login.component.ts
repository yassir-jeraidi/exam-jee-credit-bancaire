import { Component } from '@angular/core';
import { NgIf, NgOptimizedImage } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ NgIf, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    const formData = this.loginForm.value;

    console.log(formData);

    this.authService.login(formData).subscribe({
      next: (response) => {
        console.log('Login successful', response);
        localStorage.setItem('access_token', response.accessToken);
        localStorage.setItem('refresh_token', response.refreshToken);
        const redirectPath = this.authService.getRedirectPath(response.role);
        this.router.navigate([redirectPath]);
      },
      error: (error) => {
        console.error('Login failed', error);
        alert('Login failed. Please check your credentials and try again.');
        this.submitted = false;
        this.loginForm.reset();
      },
    });
  }
}
