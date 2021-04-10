export class Vehicle {
  constructor(
      public vehicleId: number,
      public cityId: number,
      public vehicleName: string,
      public manufacturer: string,
      public energytype: string,
      public seats: number,
      public productionYear: Date,
      public isManual: boolean,
      public plateNumber: number,
      public insuranceNumber: number,
      public horsePower: number,
      public cost: number,
      public conditionDescription: string
    ){}
}