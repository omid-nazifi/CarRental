import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { Vehicle } from '../models/Vehicle';


@Injectable({
  providedIn: 'root'
})
export class VehiclesService {
  private baseUrl = "http://localhost:7000/vehicle/all";

  constructor(private http: HttpClient) { }

  getVehicles(): Observable <Vehicle[]>{
    const url = `${this.baseUrl}`;
    return this.http
      .get(url)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new Vehicle(item.vehicleId, item.cityId, item.vehicleName, item.manufacturer, item.energytype, item.seats, item.productionYear, item.isManual, item.plateNumber, item.insuranceNumber, item.horsePower, item.cost, item.conditionDescription, item.url)
          )
        )
      );
  }
}