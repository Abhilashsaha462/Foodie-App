import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Bill } from '../Model-Classes/Bill';
import { LinkService } from '../Services/link.service';
declare function paymentStart():void;

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

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
  
  ngOnInit(): void {
    this.activate.paramMap.subscribe((x)=>{
      var p = JSON.parse(localStorage.getItem('list')!);
      console.log("p is",p);
      
      
      this.link.placeOrder(p).subscribe((x)=>{
        console.log("Order Data",x);
        
        this.RestForm.patchValue(x);
        this.bills=x.bill;
       
        console.log("RestForm is",this.RestForm.value);
        
      })
    })
  }
  get add() {
    return (this.RestForm.get('user') as FormGroup).get('address') as FormArray;
  }
  get menu(){
    return (this.RestForm.get('bill') as FormGroup).get('menuList') as FormArray;
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
