import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonCard } from './person-card';

describe('PersonCard', () => {
  let component: PersonCard;
  let fixture: ComponentFixture<PersonCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonCard],
    }).compileComponents();

    fixture = TestBed.createComponent(PersonCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
