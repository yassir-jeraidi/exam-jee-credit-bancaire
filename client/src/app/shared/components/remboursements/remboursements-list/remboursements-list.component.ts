import { Component } from '@angular/core';
import {CurrencyPipe, DatePipe, NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {LoadingComponent} from '../../loading/loading.component';
import {Remboursement} from '../../../../core/models/remboursement.model';
import {Credit} from '../../../../core/models/credit.model';
import {RemboursementService} from '../../../../core/services/remboursement.service';
import {CreditService} from '../../../../core/services/credit.service';

@Component({
  selector: 'app-remboursements-list',
  imports: [
    CurrencyPipe,
    DatePipe,
    FormsModule,
    LoadingComponent,
    NgForOf,
    NgIf
  ],
  templateUrl: './remboursements-list.component.html',
  styleUrl: './remboursements-list.component.css'
})
export class RemboursementsListComponent {
  remboursements: Remboursement[] = [];
  credits: Credit[] = [];
  isLoading = false;
  showModal = false;
  isEditing = false;
  selectedRemboursement: Remboursement = {
    montant: 0,
    dateRemboursement: new Date(),
    creditId: 0,
  };

  constructor(
    private remboursementService: RemboursementService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.loadRemboursements();
    this.loadCredits();
  }

  loadRemboursements(): void {
    this.isLoading = true;
    this.remboursementService.getAllRemboursements().subscribe({
      next: (data) => {
        this.remboursements = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading remboursements:', error);
        this.isLoading = false;
      },
    });
  }

  loadCredits(): void {
    this.creditService.getAllCredits().subscribe({
      next: (data) => {
        this.credits = data;
      },
      error: (error) => console.error('Error loading credits:', error),
    });
  }

  openCreateModal(): void {
    this.isEditing = false;
    this.selectedRemboursement = {
      montant: 0,
      dateRemboursement: new Date(),
      creditId: 0,
    };
    this.showModal = true;
  }

  openEditModal(remboursement: Remboursement): void {
    this.isEditing = true;
    this.selectedRemboursement = { ...remboursement };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  onSubmit(): void {
    if (this.isEditing) {
      this.remboursementService
        .updateRemboursement(
          this.selectedRemboursement.id!,
          this.selectedRemboursement
        )
        .subscribe({
          next: () => {
            this.loadRemboursements();
            this.closeModal();
          },
          error: (error) =>
            console.error('Error updating remboursement:', error),
        });
    } else {
      this.remboursementService
        .createRemboursement(this.selectedRemboursement)
        .subscribe({
          next: () => {
            this.loadRemboursements();
            this.closeModal();
          },
          error: (error) =>
            console.error('Error creating remboursement:', error),
        });
    }
  }

  deleteRemboursement(id: number): void {
    if (confirm('Are you sure you want to delete this remboursement?')) {
      this.remboursementService.deleteRemboursement(id).subscribe({
        next: () => this.loadRemboursements(),
        error: (error) => console.error('Error deleting remboursement:', error),
      });
    }
  }
}
