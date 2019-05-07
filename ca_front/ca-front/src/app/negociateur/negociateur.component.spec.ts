import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociateurComponent } from './negociateur.component';

describe('NegociateurComponent', () => {
  let component: NegociateurComponent;
  let fixture: ComponentFixture<NegociateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
