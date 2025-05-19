import {Client} from './client.model';

export interface Credit {
  id?: number;
  montant: number;
  taux: number;
  duree: number;
  dateDemande: Date;
  clientId: number;
  client?: Client;
}
