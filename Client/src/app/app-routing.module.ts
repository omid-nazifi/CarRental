import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminCenterComponent } from './components/admin-center/admin-center.component';
import { CarOfferComponent } from './components/car-offer/car-offer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RentCarComponent } from './components/rent-car/rent-car.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'admin-center', component:AdminCenterComponent},
  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'car-offer', component:CarOfferComponent},
  {path:'rent-car', component:RentCarComponent},
  {path:'register', component:RegisterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
