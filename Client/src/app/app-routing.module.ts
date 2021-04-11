import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SiteNavigationComponent } from './components/site-navigation/site-navigation.component';
import { LoginComponent } from './components/login/login.component';
import { AdminCenterComponent } from './components/admin-center/admin-center.component';
import { CarOfferComponent } from './components/car-offer/car-offer.component';
import { RegisterComponent } from './components/register/register.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';

const routes: Routes = [
  {path: '', redirectTo:'login', pathMatch:'full'},
  {path: 'admin-center', component:AdminCenterComponent},
  {path: 'admin-login', component:AdminLoginComponent},
  {path: 'car-offer', component:CarOfferComponent},
  {path: 'login', component:LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'rent-car', component:RentCarComponent},
  {path: 'site-navigation', component:SiteNavigationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
