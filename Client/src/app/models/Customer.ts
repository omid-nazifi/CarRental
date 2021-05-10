export class Customer {
  constructor(
      public customerId: string,
      public personalId: number,
      public countryId: number,
      public firstName: string,
      public lastName: string,
      public address: string,
      public email: string,
      public number: string
    ){}
}