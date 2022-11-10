import { Address } from "./address";

export class User{
    email:string;
    userName:string;
    phoneNo:string;
    address:Address[];
    constructor(){
        this.email="";
        this.userName="";
        this.phoneNo="";
        this.address=[];
    }
}