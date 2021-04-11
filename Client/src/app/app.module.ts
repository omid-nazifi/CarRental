import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CustomersService } from './services/customers.service';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { SiteNavigationComponent } from './components/site-navigation/site-navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { WelcomeScreenComponent } from './components/welcome-screen/welcome-screen.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { AdminCenterComponent } from './components/admin-center/admin-center.component';
import { CarOfferComponent } from './components/car-offer/car-offer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';
import { RestapiService } from './services/restapi.service';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';



@NgModule({
  declarations: [
    AppComponent,
    SiteNavigationComponent,
    WelcomeScreenComponent,
    AdminCenterComponent,
    CarOfferComponent,
    LoginComponent,
    RegisterComponent,
    RentCarComponent,
    AdminLoginComponent
  
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatToolbarModule, 
    MatTableModule, 
    BrowserAnimationsModule,
    MatButtonModule,
    LayoutModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatTabsModule,
    MatCheckboxModule,
    MatDatepickerModule
    
  ],
  providers: [
    CustomersService,
    RestapiService
  ],
  bootstrap: [AppComponent]

})
export class AppModule { }
