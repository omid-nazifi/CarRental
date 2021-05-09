import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  form: any = {
    email: null,
    password: null,
    firstName: null,
    lastName: null,
    personalID: null,
    address: null,
    phoneNumber: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }



  ngOnInit() {
  }

  onSubmit(): void {
    const { email, password, firstName, lastName, personalID, address, phoneNumber } = this.form;

    this.authService.register(email, password, firstName, lastName, personalID, address, phoneNumber).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
