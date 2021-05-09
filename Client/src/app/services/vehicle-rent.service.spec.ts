import { TestBed } from '@angular/core/testing';

import { VehicleRentService } from './vehicle-rent.service';

describe('VehicleRentService', () => {
  let service: VehicleRentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehicleRentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
