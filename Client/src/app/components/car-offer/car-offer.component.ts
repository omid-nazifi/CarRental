import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';

@Component({
  selector: 'app-car-offer',
  templateUrl: './car-offer.component.html',
  styleUrls: ['./car-offer.component.css']
})
export class CarOfferComponent {
  /** Based on the screen size, switch from standard to one column per row */
  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Tesla Model 3', url: 'https://png2.cleanpng.com/sh/8344065330b37fc981d060b18bf7f86a/L0KzQYm3VsA6N6VqiZH0aYP2gLBuTgRme51mRd94ZHXvPYS0lPV0dJIyhdH9b4L2PbTokr1mdJZojORyYz35dbnwgBxmNaVqi95qLX3ydLbzTcMuPZM2e6hsYkHmQLO3g8MvOmY9S6c6Nke0RYOBVcg6PGkAT6oEMz7zfri=/kisspng-tesla-model-3-tesla-motors-car-electric-vehicle-tesla-model-3-5b1c6cb1c0b0c3.2583516715285894897893.png',
           ps:'462 PS', 
           seats:'5', 
           productionYear:'2020', 
           consumptionAVG:'14.9kWh/100km', 
           zeroToHundred:'4.3s', 
           isManualTransmission:false, 
           plateNumber:'W-RND01',
           insuranceNumber:'INSNR01',  
           priceEUR:260, priceDOL:307, cols: 1, rows: 1 },


          { title: 'Audi Q7', url: 'https://png2.cleanpng.com/sh/6b33d8a8cc73e5f6ea4bfef0902eb7b2/L0KzQYm3VsA2N6FBj5H0aYP2gLBuTcIxOWkyeedtaT30R366TcB1NaF3fd9ydX2wg8b9TcIxOWkyeedtaT30R365TcB1NaF3RdN2cHX1cX68gsE3P2JmfqduMXW1SHA6V8k4OmU6TKMAMki0SYi8VcE0QGY6RuJ3Zx==/kisspng-2018-audi-q7-3-0t-premium-suv-2018-audi-q7-2-0t-pr-ampera-5b1671af5e1e28.3797245415281975513855.png', 
          ps:'340 PS', 
          seats:'7', 
          productionYear:'2017', 
          consumptionAVG:'6,6L/100km', 
          zeroToHundred:'6.3s', 
          isManualTransmission:false, 
          plateNumber:'W-RND02',
          insuranceNumber:'INSNR02',  
          priceEUR:300, priceDOL:354,  cols: 1, rows: 1 },


          { title: 'Fiat 500', url: 'https://png2.cleanpng.com/sh/43d9e3b3326ca5a899468bc70a27b31d/L0KzQYm3VsI3N5h8iJH0aYP2gLBuTcIxOWgyfttqdD24QIG0UsAyQF5rgdN9LUWzQH7tifF1NZJ6jNH2b3LsfLb6TfNiel46eqU6Y0Dndom5gsdjOV87SqsAOEO6RYK8Usk6QGg3UacAM0W3PsH1h5==/kisspng-2017-fiat-500-2018-fiat-500-fiat-automobiles-car-5b31c0df82b7b1.6295837515299872955354.png', 
          ps:'462 PS', 
          seats:'4', 
          productionYear:'2015', 
          consumptionAVG:'5.1L/100km', 
          zeroToHundred:'12.9s', 
          isManualTransmission:true, 
          plateNumber:'W-RND03',
          insuranceNumber:'INSNR03', 
          priceEUR:196, priceDOL:235,  cols: 1, rows: 1 },


          { title: 'Mercedes Benz C-Class', url: 'https://png2.cleanpng.com/sh/7591be6e41f35332e6dee732db051f61/L0KzQYm3V8A2N6p5j5H0aYP2gLBuTcIxOWcyhdd7Y3XndcS0gvVvgl5oRdV1YYP2PYO3UccudZZ3e9dtZYOwcrb1mr1kNZRxRdU8MECwfMbBlgJ6NWVyeeZyYz24coTrgcE1OJU6Sak5OD62Q4mBVMgyO2I6S6ICNkW5Q4OBV8I5NqFzf3==/kisspng-2016-mercedes-benz-c-class-2017-mercedes-benz-c-cl-c300-luxury-4matic-5b3da140d51708.3388481315307656328728.png', 
          ps:'184', 
          seats:'5', 
          productionYear:'2018', 
          consumptionAVG:'6.1L/100km', 
          zeroToHundred:'9.8s', 
          isManualTransmission:false,
          plateNumber:'W-RND04',
          insuranceNumber:'INSNR04',   
          priceEUR:235, priceDOL:278, cols: 1, rows: 1  }
        ];
      }

      return [
        { title: 'Tesla Model 3', url: 'https://png2.cleanpng.com/sh/8344065330b37fc981d060b18bf7f86a/L0KzQYm3VsA6N6VqiZH0aYP2gLBuTgRme51mRd94ZHXvPYS0lPV0dJIyhdH9b4L2PbTokr1mdJZojORyYz35dbnwgBxmNaVqi95qLX3ydLbzTcMuPZM2e6hsYkHmQLO3g8MvOmY9S6c6Nke0RYOBVcg6PGkAT6oEMz7zfri=/kisspng-tesla-model-3-tesla-motors-car-electric-vehicle-tesla-model-3-5b1c6cb1c0b0c3.2583516715285894897893.png', 
        ps:'462 PS', 
        seats:'5', 
        productionYear:'2020', 
        consumptionAVG:'14.9kWh/100km', 
        zeroToHundred:'4.3s', 
        isManualTransmission:false,
        plateNumber:'W-RND01',
        insuranceNumber:'INSNR01',   
        priceEUR:260, priceDOL:307, cols: 1, rows: 1 },


        { title: 'Audi Q7', url: 'https://png2.cleanpng.com/sh/6b33d8a8cc73e5f6ea4bfef0902eb7b2/L0KzQYm3VsA2N6FBj5H0aYP2gLBuTcIxOWkyeedtaT30R366TcB1NaF3fd9ydX2wg8b9TcIxOWkyeedtaT30R365TcB1NaF3RdN2cHX1cX68gsE3P2JmfqduMXW1SHA6V8k4OmU6TKMAMki0SYi8VcE0QGY6RuJ3Zx==/kisspng-2018-audi-q7-3-0t-premium-suv-2018-audi-q7-2-0t-pr-ampera-5b1671af5e1e28.3797245415281975513855.png', 
        ps:'340 PS', 
        seats:'7', 
        productionYear:'2017', 
        consumptionAVG:'6,6L/100km', 
        zeroToHundred:'6.3s', 
        isManualTransmission:false,
        plateNumber:'W-RND02',
        insuranceNumber:'INSNR02',   
        priceEUR:300, priceDOL:354,  cols: 1, rows: 1 },


        { title: 'Fiat 500', url: 'https://png2.cleanpng.com/sh/43d9e3b3326ca5a899468bc70a27b31d/L0KzQYm3VsI3N5h8iJH0aYP2gLBuTcIxOWgyfttqdD24QIG0UsAyQF5rgdN9LUWzQH7tifF1NZJ6jNH2b3LsfLb6TfNiel46eqU6Y0Dndom5gsdjOV87SqsAOEO6RYK8Usk6QGg3UacAM0W3PsH1h5==/kisspng-2017-fiat-500-2018-fiat-500-fiat-automobiles-car-5b31c0df82b7b1.6295837515299872955354.png', 
        ps:'69', 
        seats:'4', 
        productionYear:'2015', 
        consumptionAVG:'5.1L/100km', 
        zeroToHundred:'12.9s', 
        isManualTransmission:true,
        plateNumber:'W-RND03',
        insuranceNumber:'INSNR03',   
        priceEUR:196, priceDOL:235, cols: 1, rows: 1 },


        { title: 'Mercedes Benz C-Class', url: 'https://png2.cleanpng.com/sh/7591be6e41f35332e6dee732db051f61/L0KzQYm3V8A2N6p5j5H0aYP2gLBuTcIxOWcyhdd7Y3XndcS0gvVvgl5oRdV1YYP2PYO3UccudZZ3e9dtZYOwcrb1mr1kNZRxRdU8MECwfMbBlgJ6NWVyeeZyYz24coTrgcE1OJU6Sak5OD62Q4mBVMgyO2I6S6ICNkW5Q4OBV8I5NqFzf3==/kisspng-2016-mercedes-benz-c-class-2017-mercedes-benz-c-cl-c300-luxury-4matic-5b3da140d51708.3388481315307656328728.png', 
        ps:'184', 
        seats:'5', 
        productionYear:'2018', 
        consumptionAVG:'6.1L/100km', 
        zeroToHundred:'9.8s', 
        isManualTransmission:false,
        plateNumber:'W-RND04',
        insuranceNumber:'INSNR04',      
        priceEUR:235, priceDOL:278, cols: 1, rows: 1 }
      ];
    })
  );

  constructor(private breakpointObserver: BreakpointObserver) {}
}
