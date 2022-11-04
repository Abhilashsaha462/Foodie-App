import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, SelectMultipleControlValueAccessor, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSelectionList } from '@angular/material/list';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Menu } from 'src/app/Model-Classes/Menu';
import { Order } from 'src/app/Model-Classes/Order';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { OrderComponent } from 'src/app/order/order.component';
import { LinkService } from 'src/app/Services/link.service';

@Component({
  selector: 'app-display-restaurant',
  templateUrl: './display-restaurant.component.html',
  styleUrls: ['./display-restaurant.component.css']
})
export class DisplayRestaurantComponent implements OnInit {

  RestForm:FormGroup;
  thumbnail: any;
  constructor(private formbuilder:FormBuilder,private link:LinkService,private activate:ActivatedRoute,private sanitizer:DomSanitizer,
    public dialog: MatDialog) { 
    this.RestForm = this.formbuilder.group({
      restId: new FormControl('',[Validators.required]),
      restName: new FormControl('',[Validators.required]),
      city:new FormControl('',[Validators.required]),
      url:new FormControl('',[]),
    })
  }
data:any;
restaurant: Restaurant = new Restaurant();
  ngOnInit(): void {

    
   this.activate.paramMap.subscribe((x)=>{
     let k = localStorage.getItem('restName');
     console.log("x",k);
     this.link.getRestaurant(k).subscribe(x=>{
       this.restaurant = x;
       
       
       this.data = 'data:image/jpg;base64,' +x.url;
        this.RestForm.get('restName')?.setValue(this.restaurant.restName)
        this.RestForm.get('city')?.setValue(this.restaurant.city)
       
       let imageData = 'data:image/jpeg;base64,' +x.image;
      this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(imageData);
     })
   });
   this.cartDetails();
   
}
myData:any;myData1:any;

  menuList:any;
  get add() {
    return this.RestForm.get('menuList') as FormArray;
  }
  get rest(){
    return this.RestForm.controls;
  }
  addNewRow() {
   this.menuList = this.RestForm.get("menuList") as FormArray;
    this.menuList.push(this.Genrow())
  }
  Genrow(): FormGroup {
    return new FormGroup({
     itemName:new FormControl('',Validators.required),
     price:new FormControl('',Validators.required)
    });
  }
  cartData:any=[];
  cartDetails(){
   let count:number=0;
   for(let i=0;i<this.menuList1.length;i++){
     if(this.menuList1[i].itemName==this.menuList1[i+1].itemName){
      count=count+1;
     }
     this.cartData=count;
   }
  }
  getCount(itemName) {
    return this.menuList1.filter(o => o.itemName === itemName).length;
  }
  menuList1:Menu[]=[]
  increase(pass:any,event){
    console.log("data",event);
  
      this.menuList1.push(pass);
      console.log("Menulist",this.menuList1);
  }
total:number=0;

decrease(pass:any,index:number){
  
    // this.menuList1.pop();
    if(index!==-1){
      this.menuList1.splice(index,1)
    console.log("Menulist Delete",this.menuList1);
    }
    
}
order:Order=new Order();
orderPlace(){
  // this.link.placeOrder(this.menuList1).subscribe((x)=>{
  //   console.log("Order Details",x);
   
   // this.order=x;
    localStorage.setItem("list",JSON.stringify(this.menuList1))
    
//  })
var l = localStorage.getItem("list");
console.log("myList1",l);

  const dialogRef = this.dialog.open(OrderComponent, {
    width : '65%',
    height : '750px'
  });
  
  dialogRef.afterClosed().subscribe(result => {
    console.log('The Dialog was closed!');
  })
}
addToFav(){
  // this.RestForm.get('restId')?.setValue(pass.restId);
  // this.RestForm.get('restName')?.setValue(pass.restName);
  // this.RestForm.get('city')?.setValue(pass.city);
  // console.log("pass",pass.restId);
  // let imageData = 'data:image/jpeg;base64,' +pass.image;
  // this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(imageData);
  
  this.link.addFav(this.restaurant).subscribe((x)=>{
    console.log("List Added",this.restaurant);
     
 })
}
logout(){
  
}

}
