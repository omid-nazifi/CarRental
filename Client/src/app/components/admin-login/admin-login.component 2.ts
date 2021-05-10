import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { RestapiService } from 'src/app/services/restapi.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

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
      this.router.navigate(["/admin-center"])
    })
  }

}
