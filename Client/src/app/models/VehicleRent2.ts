export class VehicleRent2 {
    constructor(
        public vehicleId: number,
        public customerId: number,
        public reservationDate: string,
        public pickUpDate: string,
        public dropOffDate: string,
        public totalCost: number,
        public isDamaged: boolean
      ){}
  }