import {Credit} from './credit.model';

export interface Remboursement {
  id?: number;
  montant: number;
  dateRemboursement: Date;
  creditId: number;
  credit?: Credit;
}
