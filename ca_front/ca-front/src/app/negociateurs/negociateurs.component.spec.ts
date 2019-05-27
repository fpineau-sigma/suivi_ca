import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociateursComponent } from './negociateurs.component';

describe('NegociateursComponent', () => {
  let component: NegociateursComponent;
  let fixture: ComponentFixture<NegociateursComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociateursComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociateursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
