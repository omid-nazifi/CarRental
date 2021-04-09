import { Component } from '@angular/core';

export interface RentNDriveUser {
  id: number;
  firstName: string;
  secondName: string;
  address: string;
  phone: string;
  email: string;
}

const ELEMENT_DATA: RentNDriveUser[] = [
  {id: 1, firstName: 'Antonio', secondName: 'Lastro', address: 'FH Campus', phone: '0676 1234 567', email: 'fh@campus.at'},
  {id: 2, firstName: 'Omid', secondName: 'Nazifi', address: 'FH Campus', phone: '0676 1234 567', email: 'fh@campus.at'},
  {id: 3, firstName: 'Navid', secondName: 'Nazifi', address: 'FH Campus', phone: '0676 1234 567', email: 'fh@campus.at'},
  {id: 4, firstName: 'Fahmi', secondName: 'El-Ghamrawi', address: 'FH Campus', phone: '0676 1234 567', email: 'fh@campus.at'},
  {id: 5, firstName: 'Lukas', secondName: 'Cislo', address: 'FH Campus', phone: '0676 1234 567', email: 'fh@campus.at'},
];

@Component({
  selector: 'app-admin-center',
  templateUrl: './admin-center.component.html',
  styleUrls: ['./admin-center.component.css']
})
export class AdminCenterComponent  {

  displayedColumns: string[] = ['id', 'firstName', 'secondName', 'address', 'phone', 'email'];
  dataSource = ELEMENT_DATA;

  

}
