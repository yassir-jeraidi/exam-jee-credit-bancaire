import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment';
import {Remboursement} from '../models/remboursement.model';

@Injectable({
  providedIn: 'root',
})
export class RemboursementService {
  private apiUrl = `${environment.apiUrl}/remboursements`;

  constructor(private http: HttpClient) {}

  getAllRemboursements(): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(this.apiUrl);
  }

  getRemboursementById(id: number): Observable<Remboursement> {
    return this.http.get<Remboursement>(`${this.apiUrl}/${id}`);
  }

  getRemboursementsByCreditId(creditId: number): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(`${this.apiUrl}/credit/${creditId}`);
  }

  createRemboursement(remboursement: Remboursement): Observable<Remboursement> {
    return this.http.post<Remboursement>(this.apiUrl, remboursement);
  }

  updateRemboursement(
    id: number,
    remboursement: Remboursement
  ): Observable<Remboursement> {
    return this.http.put<Remboursement>(`${this.apiUrl}/${id}`, remboursement);
  }

  deleteRemboursement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
