import { Address } from "./address";
import { Restaurant } from "./Restaurant";

export class reg
{
    email:string;
    password:string;
    userName:string;
    phoneNo:string;
    address:Address[];
    // favorites:Restaurant[];
    constructor(){
    this.email="",
    this.password="",
    this.userName="",
    this.phoneNo="",
    this.address=[]
    // this.favorites=[]
}
}