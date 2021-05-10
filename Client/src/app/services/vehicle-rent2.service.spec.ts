import { TestBed } from '@angular/core/testing';

import { VehicleRent2Service } from './vehicle-rent2.service';

describe('VehicleRent2Service', () => {
  let service: VehicleRent2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehicleRent2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
