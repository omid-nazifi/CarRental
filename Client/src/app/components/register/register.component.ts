import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { RestapiService } from 'src/app/services/restapi.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  personalId : number;
  firstName : string;
  lastName : string;
  address : string;
  email : string;
  number : string;
  password : string;
  message : any;

  constructor(private service: RestapiService, private router: Router) { }



  ngOnInit() {
  }

  doRegister(){
    let response = this.service.register(this.personalId, this.firstName, this.lastName, this.address, this.email, this.number, this.password)
    response.subscribe(data=>{
      this.message = data;
      this.router.navigate(["/login"])
    })
  }

}
