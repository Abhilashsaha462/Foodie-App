import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Bill } from '../Model-Classes/Bill';
import { LinkService } from '../Services/link.service';
import * as _ from 'lodash';
import { Menu } from '../Model-Classes/Menu';
import Swal from 'sweetalert2';
Pipe({
  name: 'unique',
  pure: false
})
declare function paymentStart():void;
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit,PipeTransform {
RestForm:FormGroup;
bills: Bill = new Bill();
  constructor(private activate:ActivatedRoute,private link:LinkService,private formbuilder:FormBuilder,
    private router:Router) {
    this.RestForm = this.formbuilder.group({
      orderId:new FormControl([]),
      user:this.formbuilder.group({
        email:new FormControl('',[]),
        userName:new FormControl('',[]),
        phoneNo:new FormControl('',[]),
        address:this.formbuilder.array([
          this.formbuilder.group({
            homeNo:new FormControl('',[Validators.required]),
            roadName:new FormControl('',[Validators.required]),
            city:new FormControl('',[Validators.required]),
            pincode:new FormControl('',[Validators.required])
          })
        ])
      }),
        bill: this.formbuilder.group({
          billId: new FormControl('',[]),
          menuList: this.formbuilder.array([
            this.formbuilder.group({
              itemName: new FormControl('',[Validators.required]),
              price: new FormControl('',[Validators.required])
            })
          ]),
          totalPrice:new FormControl('',[])
        })
      
    })
   }
  
     transform(value: any): any{
      if(value!== undefined && value!== null){
          return _.uniqBy(value, 'name');
      }
      return value;
  }
  k:number | undefined;
  myList:Menu[]=[];
  ngOnInit(): void {
    this.activate.paramMap.subscribe((x)=>{
      var p = JSON.parse(localStorage.getItem('list')!);
      this.link.placeOrder(p).subscribe((x)=>{
        console.log("order",x);
      // const v = x.bill.menuList.distinct();
        this.RestForm.patchValue(x);
        this.bills=x.bill;
        this.k=x.orderId;
        //  this.k = [...new Set(this.bills.menuList.flatMap(l=> l.itemName))];
      
      })
    })
  }
  get add() {
    return (this.RestForm.get('user') as FormGroup).get('address') as FormArray;
  }
  get menu(){
    return (this.RestForm.get('bill') as FormGroup).get('menuList') as FormArray;
  }
  cancleOrder(){
    console.log("k",this.k);
    
    this.link.deleteOrder(this.k).subscribe(x=>{
     
      
      Swal.fire("Done...!!!", "Order Cancled Successfully...!!!", "success");
      location.reload();
      
      // this.router.navigateByUrl("restaurants-details")
    })
  }

  navToHome(){
    this.router.navigateByUrl('');
  }
  // getTotalCost() {
  //   return this..map(t => t.cost).reduce((acc, value) => acc + value, 0);
  // }
  g(){
    new paymentStart();
  }

}
// export class UniquePipe implements PipeTransform {
//   transform(value: any): any{
//       if(value!== undefined && value!== null){
//           return _.uniqBy(value, 'name');
//       }
//       return value;
//   }
// }
