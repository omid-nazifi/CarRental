import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:7000/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      email,
      password
    }, httpOptions);
  }


  register(email: string, password: string, firstName: string, lastName: string, personalID: string, address: string, phoneNumber: string): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      email,
      password,
      firstName,
      lastName,
      personalID,
      address,
      phoneNumber
    }, httpOptions);
  }
}
