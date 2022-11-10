import { Menu } from "./Menu";

export class Bill{
    billId:number;
    menuList:Menu[];
    totalPrice:number;
    constructor(){
        this.billId=0,
        this.menuList=[],
        this.totalPrice=0
    }
}