import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { VehiclesService } from 'src/app/services/vehicles.service';
import { Vehicle } from '../../models/Vehicle';

@Component({
  selector: 'app-car-offer',
  templateUrl: './car-offer.component.html',
  styleUrls: ['./car-offer.component.scss']
})
export class CarOfferComponent implements OnInit {

  /*cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Tesla Model 3', url: 'https://png2.cleanpng.com/sh/b6baf05ce384256e5585346548bd0bd7/L0KzQYm3VsA6N6VqiZH0aYP2gLBuTgRme51mRd94ZHXvPYS0lPV0dJIyhdH9b4L2PbTokr1mdJZojORyYz35dbnwgBxmNaVqi95qLX3ydLbzTcMuPZM2e6hsYkHmQLO3g8MvOmY9S6c6Nke0RYOBVcg6PGkAT6oEMz7zfri=/kisspng-tesla-model-3-tesla-motors-car-electric-vehicle-tesla-model-3-5b1c6cb1c0b0c3.2583516715285894897893.png', cols: 1, rows: 1 },
          { title: 'Ferrari', url: 'https://png2.cleanpng.com/sh/1cd20653a5a6b4a5ec4faff58ed4acc6/L0KzQYm3V8I4N6t1j5H0aYP2gLBuTcIxOWkyeedtaT30R37olfRqNaR2T587MEG6PbL8hPkueWgye9N7LXH4dLq0kccuPZM6eqo7ZkG5dIi7gcAvQWc7SqMENUa0RYS5V8I0QWY4TKYDMz7zfri=/kisspng-2018-audi-q7-audi-sq7-2017-audi-q7-car-audi-q7-5b5b82f16d74a0.9662195615327239534483.png',cols: 1, rows: 1 },
          { title: 'Fiat 500', url: 'https://png2.cleanpng.com/sh/4f49e3baf4c8861a6f258e55f2634825/L0KzQYm3WcI3N5d4gZH0aYP2gLBuTcIuOWUyfttqdD24PbfwggQuaaZ5h994YnnvdcS0Ur0yPl5rgdN9LUWwc7L5TfZqaaUyTZ9qYnH1hLm0Ur0yP154jNc2ZnB8PcH5k71lbV5xjtt8LUXlcbS7VsAyPmhpUaYDLkSzQ4KAUME3OWY4UKI6Nke5SYW5VcQveJ9s/kisspng-2-14-fiat-5-fiat-automobiles-2-16-fiat-5-car-fiat-5-abarth-2-17-ste-foy-prs-de-lvis-5bac460167d948.4031701615380167694254.png', cols: 1, rows: 1 },
          { title: 'Mercedes Benz C-Class', url: 'https://png2.cleanpng.com/sh/c4dfd40e44f76bccfc7231889fe593b2/L0KzQYm3V8I6N6Z0h5H0aYP2gLBuTcIxOWYyhdd7Y3XndcS0gvVvgl5oRdV1YYP2Pb7skvNmbJZ4RdRuboqwcX7qjPF0e15yRd9ucnPodLb6TfMua51miAU2NXK4dLeCVsA4bJVmfqk3MEC3Roi6U8MyPWQ3UKoAM0S3RYK8Vb5xdpg=/kisspng-2015-mercedes-benz-c-class-mercedes-benz-a-class-m-mercedes-c-class-5b5df9607ddaf7.0046733315328853445155.png', cols: 1, rows: 1 }
        ];
      }

      return [
        { title: 'Tesla Model 3', url: 'https://png2.cleanpng.com/sh/b6baf05ce384256e5585346548bd0bd7/L0KzQYm3VsA6N6VqiZH0aYP2gLBuTgRme51mRd94ZHXvPYS0lPV0dJIyhdH9b4L2PbTokr1mdJZojORyYz35dbnwgBxmNaVqi95qLX3ydLbzTcMuPZM2e6hsYkHmQLO3g8MvOmY9S6c6Nke0RYOBVcg6PGkAT6oEMz7zfri=/kisspng-tesla-model-3-tesla-motors-car-electric-vehicle-tesla-model-3-5b1c6cb1c0b0c3.2583516715285894897893.png', cols: 1, rows: 1 },
        { title: 'Ferrari', url: 'https://png2.cleanpng.com/sh/1cd20653a5a6b4a5ec4faff58ed4acc6/L0KzQYm3V8I4N6t1j5H0aYP2gLBuTcIxOWkyeedtaT30R37olfRqNaR2T587MEG6PbL8hPkueWgye9N7LXH4dLq0kccuPZM6eqo7ZkG5dIi7gcAvQWc7SqMENUa0RYS5V8I0QWY4TKYDMz7zfri=/kisspng-2018-audi-q7-audi-sq7-2017-audi-q7-car-audi-q7-5b5b82f16d74a0.9662195615327239534483.png', cols: 1, rows: 1 },
        { title: 'Fiat 500', url: 'https://png2.cleanpng.com/sh/4f49e3baf4c8861a6f258e55f2634825/L0KzQYm3WcI3N5d4gZH0aYP2gLBuTcIuOWUyfttqdD24PbfwggQuaaZ5h994YnnvdcS0Ur0yPl5rgdN9LUWwc7L5TfZqaaUyTZ9qYnH1hLm0Ur0yP154jNc2ZnB8PcH5k71lbV5xjtt8LUXlcbS7VsAyPmhpUaYDLkSzQ4KAUME3OWY4UKI6Nke5SYW5VcQveJ9s/kisspng-2-14-fiat-5-fiat-automobiles-2-16-fiat-5-car-fiat-5-abarth-2-17-ste-foy-prs-de-lvis-5bac460167d948.4031701615380167694254.png', cols: 1, rows: 1 },
        { title: 'Mercedes Benz C-Class', url: 'https://png2.cleanpng.com/sh/c4dfd40e44f76bccfc7231889fe593b2/L0KzQYm3V8I6N6Z0h5H0aYP2gLBuTcIxOWYyhdd7Y3XndcS0gvVvgl5oRdV1YYP2Pb7skvNmbJZ4RdRuboqwcX7qjPF0e15yRd9ucnPodLb6TfMua51miAU2NXK4dLeCVsA4bJVmfqk3MEC3Roi6U8MyPWQ3UKoAM0S3RYK8Vb5xdpg=/kisspng-2015-mercedes-benz-c-class-mercedes-benz-a-class-m-mercedes-c-class-5b5df9607ddaf7.0046733315328853445155.png', cols: 1, rows: 1 }
      ];
    })
  );*/

  vehicles: Vehicle[];
  vehiclesService: VehiclesService;

  constructor(vehiclesService: VehiclesService) {
    this.vehiclesService=vehiclesService;
  }

  ngOnInit(): void {
    this.vehiclesService.getVehicles().subscribe(res => {
      console.log(res);
      this.vehicles=res;
    },err => {
      console.log(err);
    })
  }

  
}
