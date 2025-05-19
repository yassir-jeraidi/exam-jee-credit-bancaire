import { Component } from '@angular/core';
import {CurrencyPipe, DatePipe, NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {LoadingComponent} from "../../loading/loading.component";
import {Credit} from '../../../../core/models/credit.model';
import {Client} from '../../../../core/models/client.model';
import {CreditService} from '../../../../core/services/credit.service';
import {ClientService} from '../../../../core/services/client.service';

@Component({
  selector: 'app-credits-list',
    imports: [
        CurrencyPipe,
        DatePipe,
        FormsModule,
        LoadingComponent,
        NgForOf,
        NgIf
    ],
  templateUrl: './credits-list.component.html',
  styleUrl: './credits-list.component.css'
})
export class CreditsListComponent {
  credits: Credit[] = [];
  clients: Client[] = [];
  isLoading = false;
  showModal = false;
  isEditing = false;
  selectedCredit: Credit = {
    montant: 0,
    taux: 0,
    duree: 0,
    dateDemande: new Date(),
    clientId: 0,
  };

  constructor(
    private creditService: CreditService,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.loadCredits();
    this.loadClients();
  }

  loadCredits(): void {
    this.isLoading = true;
    this.creditService.getAllCredits().subscribe({
      next: (data) => {
        console.log(data)
        this.credits = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading credits:', error);
        this.isLoading = false;
      },
    });
  }

  loadClients(): void {
    this.clientService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
      },
      error: (error) => console.error('Error loading clients:', error),
    });
  }

  openCreateModal(): void {
    this.isEditing = false;
    this.selectedCredit = {
      montant: 0,
      taux: 0,
      duree: 0,
      dateDemande: new Date(),
      clientId: 0,
    };
    this.showModal = true;
  }

  openEditModal(credit: Credit): void {
    this.isEditing = true;
    this.selectedCredit = { ...credit };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  onSubmit(): void {
    if (this.isEditing) {
      this.creditService
        .updateCredit(this.selectedCredit.id!, this.selectedCredit)
        .subscribe({
          next: () => {
            this.loadCredits();
            this.closeModal();
          },
          error: (error) => console.error('Error updating credit:', error),
        });
    } else {
      this.creditService.createCredit(this.selectedCredit).subscribe({
        next: () => {
          this.loadCredits();
          this.closeModal();
        },
        error: (error) => console.error('Error creating credit:', error),
      });
    }
  }

  deleteCredit(id: number): void {
    if (confirm('Are you sure you want to delete this credit?')) {
      this.creditService.deleteCredit(id).subscribe({
        next: () => this.loadCredits(),
        error: (error) => console.error('Error deleting credit:', error),
      });
    }
  }
}
