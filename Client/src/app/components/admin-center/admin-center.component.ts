import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { CustomersService } from 'src/app/services/customers.service';
import { Customer } from '../../models/Customer';
@Component({
  selector: 'app-admin-center',
  templateUrl: './admin-center.component.html',
  styleUrls: ['./admin-center.component.scss']
})
export class AdminCenterComponent implements OnInit {

  customers: Customer[];
  customersService: CustomersService;

  constructor(customersService: CustomersService) {
    this.customersService=customersService;
  }

  ngOnInit(): void {
    this.customersService.getCustomers().subscribe(res => {
      console.log(res);
      this.customers=res;
    },err => {
      console.log(err);
    })
  }
  

}
