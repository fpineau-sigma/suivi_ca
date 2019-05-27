import { TestBed } from '@angular/core/testing';

import { NegociateursService } from './negociateurs.service';

describe('NegociateursService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NegociateursService = TestBed.get(NegociateursService);
    expect(service).toBeTruthy();
  });
});
