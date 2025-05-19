import { TestBed } from '@angular/core/testing';

import { RemboursementsService } from './remboursements.service';

describe('RemboursementsService', () => {
  let service: RemboursementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RemboursementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
