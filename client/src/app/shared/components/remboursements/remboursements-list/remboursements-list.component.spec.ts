import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemboursementsListComponent } from './remboursements-list.component';

describe('RemboursementsListComponent', () => {
  let component: RemboursementsListComponent;
  let fixture: ComponentFixture<RemboursementsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RemboursementsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemboursementsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
