import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { RestapiService } from 'src/app/services/restapi.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  email:string;
  password:string;
  message:any

  constructor(private service: RestapiService, private router: Router) { }



  ngOnInit() {
  }

  doLogin(){
    let response = this.service.login(this.email, this.password);
    response.subscribe(data=>{
      this.message = data;
      this.router.navigate(["/car-offer"])
    })
  }

  goToRegister(){
    
      this.router.navigate(["/register"])
    }
}