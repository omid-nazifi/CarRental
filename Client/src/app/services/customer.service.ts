import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { Customer } from '../models/Customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = "http://localhost:7000/api/auth/all";

  constructor(private http: HttpClient) { }

  getCustomers(): Observable <Customer[]>{
    const url = `${this.baseUrl}`;
    return this.http
      .get(url)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new Customer(item.customerID, item.personalId, item.countryId, item.firstName, item.lastName, item.address, item.email, item.number)
          )
        )
      );
  }
}