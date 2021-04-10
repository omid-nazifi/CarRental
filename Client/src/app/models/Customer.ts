export class Customer {
  constructor(
      public customerId: number,
      public personalId: number,
      public firstName: string,
      public lastName: string,
      public address: string,
      public email: string,
      public number: string
    ){}
}