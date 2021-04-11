import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) { }

  public login(email: string, password: string){
    const headers = new HttpHeaders({Authorization: 'Basic' + btoa(email + ":" + password)})
    return this.http.get("http://localhost:7000/customer/login",{headers, responseType:'text' as 'json'})
  }

  public all(){
    let email="test";
    let password="test";
    const headers = new HttpHeaders({Authorization: 'Basic' + btoa(email + ":" + password)})
    return this.http.get("http://localhost:7000/customer/login",{headers, responseType:'text' as 'json'})
  }


  public register(personalId: number, firstName: string, lastName: string, address: string, email: string, number: string, password: string){
    const headers = new HttpHeaders({Authorization: 'Basic' + btoa(personalId + ":" + firstName + ":" + lastName + ":" + address + ":" + email + ":" +  number + ":" + password)})
    return this.http.get("http://localhost:7000/customer/register",{headers, responseType:'text' as 'json'})
  }
}
