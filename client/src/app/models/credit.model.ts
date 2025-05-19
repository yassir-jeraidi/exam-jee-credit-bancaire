export interface Credit {
  id?: number;
  montant: number;
  taux: number;
  duree: number;
  dateDebut: Date;
  clientId: number;
  client?: Client;
}
