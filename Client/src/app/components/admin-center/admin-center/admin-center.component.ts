import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../../../models/Customer';

@Component({
  selector: 'app-admin-center',
  templateUrl: './admin-center.component.html',
  styleUrls: ['./admin-center.component.scss']
})
export class AdminCenterComponent implements OnInit {

  customers: Customer[];
  customerService: CustomerService;
  Constructor(customerService: CustomerService){
    this.customerService=customerService;
  }

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe(res => {
      console.log(res);
      this.customers=res;
    },err => {
      console.log(err);
    })
  }

}
