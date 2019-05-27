import { TestBed } from '@angular/core/testing';

import { VentesService } from './ventes.service';

describe('VentesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VentesService = TestBed.get(VentesService);
    expect(service).toBeTruthy();
  });
});
