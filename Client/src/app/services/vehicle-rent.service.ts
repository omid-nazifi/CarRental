import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const VEHRENT_API = 'http://localhost:7000/vehicle/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class VehicleRentService {

  constructor(private http: HttpClient) { }

  vehicleRent(customerID: string, vehicleID: string, reservationDate: Date, pickUpDate: Date, dropOffDate, isDamaged: boolean, totalCost: number): Observable<any> {
    return this.http.post(VEHRENT_API + 'vehicleRent', {
      customerID,
      vehicleID,
      reservationDate,
      pickUpDate,
      dropOffDate,
      isDamaged,
      totalCost
    }, httpOptions);
  }
}
