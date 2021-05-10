import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Vehicle } from '../../models/Vehicle';
import { VehiclesService } from 'src/app/services/vehicles.service';

@Component({
  selector: 'app-rent-car',
  templateUrl: './rent-car.component.html',
  styleUrls: ['./rent-car.component.scss']
})
export class RentCarComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  vehicles: Vehicle[];
  vehiclesService: VehiclesService;

  constructor() { }

  ngOnInit(): void {
    //https://stackoverflow.com/questions/35688084/how-to-get-query-params-from-url-in-angular-2
  }

}
