import { FileHandle } from "./File-handle.model";
import { Menu } from "./Menu";

export class Restaurant{
    restId:number;
    restName:string;
    email:string;
    city:string;
    url:string;
    menuList:Menu[];
    selected:Boolean;
    constructor(){
        this.restId=0;
        this.restName="";
        this.email="";
        this.city="";
        this.url="";
        this.menuList=[];
        this.selected=false;
    }
}