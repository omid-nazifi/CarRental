import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationSiteComponent } from './components/navigation-site/navigation-site.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { CarOfferComponent } from './components/car-offer/car-offer.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { RegisterComponent } from './components/register/register.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { RentCarComponent } from './components/rent-car/rent-car.component';
import { AdminCenterComponent } from './components/admin-center/admin-center.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationSiteComponent,
    CarOfferComponent,
    LoginComponent,
    RegisterComponent,
    RentCarComponent,
    AdminCenterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    FormsModule,
    MatDialogModule,
    MatTableModule,
    MatInputModule, 
    MatProgressSpinnerModule,
    MatTabsModule,
    MatCheckboxModule,
    MatDatepickerModule,
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
