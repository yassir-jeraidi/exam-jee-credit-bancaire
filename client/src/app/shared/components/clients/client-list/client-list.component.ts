import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {LoadingComponent} from "../../loading/loading.component";
import {NgForOf, NgIf} from "@angular/common";
import {Client} from '../../../../core/models/client.model';
import {ClientService} from '../../../../core/services/client.service';

@Component({
  selector: 'app-client-list',
    imports: [
        FormsModule,
        LoadingComponent,
        NgForOf,
        NgIf
    ],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css'
})
export class ClientListComponent  {
  clients: Client[] = [];
  isLoading = false;
  showModal = false;
  isEditing = false;
  selectedClient: Client = {
    nom: '',
    email: '',
  };

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.isLoading = true;
    this.clientService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading clients:', error);
        this.isLoading = false;
      },
    });
  }

  openCreateModal(): void {
    this.isEditing = false;
    this.selectedClient = {
      nom: '',
      email: '',
    };
    this.showModal = true;
  }

  openEditModal(client: Client): void {
    this.isEditing = true;
    this.selectedClient = { ...client };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  onSubmit(): void {
    if (this.isEditing) {
      this.clientService
        .updateClient(this.selectedClient.id!, this.selectedClient)
        .subscribe({
          next: () => {
            this.loadClients();
            this.closeModal();
          },
          error: (error) => console.error('Error updating client:', error),
        });
    } else {
      this.clientService.createClient(this.selectedClient).subscribe({
        next: () => {
          this.loadClients();
          this.closeModal();
        },
        error: (error) => console.error('Error creating client:', error),
      });
    }
  }

  deleteClient(id: number): void {
    if (confirm('Are you sure you want to delete this client?')) {
      this.clientService.deleteClient(id).subscribe({
        next: () => this.loadClients(),
        error: (error) => console.error('Error deleting client:', error),
      });
    }
  }
}
