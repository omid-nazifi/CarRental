import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { VehiclesService } from 'src/app/services/vehicles.service';
import { Vehicle } from '../../models/Vehicle';
import {FormGroup, FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { VehicleRentService } from '../../services/vehicle-rent.service'
import { TokenStorageService } from '../../services/token-storage.service'

@Component({
  selector: 'app-car-offer',
  templateUrl: './car-offer.component.html',
  styleUrls: ['./car-offer.component.scss']
})
export class CarOfferComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  vehicles: Vehicle[];
  vehiclesService: VehiclesService;
  form: any = {
    customerID: null,
    vehicleID: null,
    reservationDate: null,
    pickUpDate: null,
    dropOffDate: null,
    isDamaged: null,
    totalCost: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(vehiclesService: VehiclesService, private router: Router, private vehicleRentService: VehicleRentService, private token: TokenStorageService) {
    this.vehiclesService=vehiclesService;
  }

  ngOnInit(): void {
    this.vehiclesService.getVehicles().subscribe(res => {
      console.log(res);
      this.vehicles=res;
    },err => {
      console.log(err);
    })
  }


  onSubmit(): void {
    const { customerID, vehicleID, reservationDate, pickUpDate, dropOffDate, isDamaged, totalCost } = this.form;

    this.vehicleRentService.vehicleRent(customerID, vehicleID, reservationDate, pickUpDate, dropOffDate, isDamaged, totalCost).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  
}
