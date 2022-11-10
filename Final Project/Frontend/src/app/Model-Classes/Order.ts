import { Bill } from "./Bill";
import { User } from "./User";

export class Order{
    orderId:number;
    user:User;
    bill:Bill;
    constructor(){
        this.orderId=0;
        this.user=new User();
        this.bill = new Bill()
    }
}