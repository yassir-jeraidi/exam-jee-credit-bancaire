import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { LoginRequest, LoginResponse } from '../interfaces/auth.interface';
import { BehaviorSubject, firstValueFrom, Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl + '/auth/';

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl + 'login', credentials);
  }

  me(): Observable<User> {
    return this.http.get<User>(this.apiUrl + 'me');
  }

  getRedirectPath(role: string): string {
    try {
      switch (role) {
        case 'ADMIN':
          return '/admin';
        case 'EMPLOYE':
          return '/employe';
        case 'CLIENT':
          return '/client';
        default:
          return '/unauthorized';
      }
    } catch (error) {
      console.error('Error fetching user', error);
      return '/unauthorized';
    }
  }
}
