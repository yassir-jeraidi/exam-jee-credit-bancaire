export enum Role {
  ADMIN = 'ADMIN',
  CLIENT = 'CLIENT',
  EMPLOYE = 'EMPLOYE',
}
export interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  role: Role;
}
