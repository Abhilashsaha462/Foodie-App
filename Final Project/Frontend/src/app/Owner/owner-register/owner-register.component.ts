import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from 'src/app/Model-Classes/address';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { LinkService } from 'src/app/Services/link.service';

@Component({
  selector: 'app-owner-register',
  templateUrl: './owner-register.component.html',
  styleUrls: ['./owner-register.component.css']
})
export class OwnerRegisterComponent implements OnInit {
  RegistryForm:FormGroup;
  tempArray1:Address | undefined;
  tempArray2:Restaurant|undefined;
  items!:FormArray;
  constructor(private register:LinkService,private formbuilder:FormBuilder,private router:Router) {
    this.RegistryForm=this.formbuilder.group({
      ownerName:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      phoneNo:new FormControl('',[Validators.required]),
      gstNo:new FormControl('',[Validators.required]),
      address:this.formbuilder.array([
        this.formbuilder.group({
          homeNo:new FormControl('',[Validators.required]),
          roadName:new FormControl('',[Validators.required]),
          city:new FormControl('',[Validators.required]),
          pincode:new FormControl('',[Validators.required])
        })
      ])
    })
   }

  ngOnInit(): void {
  }
  registerfun(){
    {
      alert("Register Successful");
      const regData = this.RegistryForm.value;
      this.addAddress();
      console.log(regData)
    this.register.ownerRegister(regData).subscribe(() => {
      alert("Register Successful");
      // location.reload();
      window.location.href="/ownerlogin";
    });
    }
  }
  get add() {
    return this.RegistryForm.get('address') as FormArray;
  }
  getControls1() {
    return (this.RegistryForm.get('favorites') as FormArray).controls;
  }
  addAddress(){
    console.log("Address data",this.items);
    this.items = this.RegistryForm.get('address') as FormArray;
    // this.tempArray1=item;
    this.items.push(this.loadData())
    
  }
  loadData():FormGroup{
    return new FormGroup({
          homeNo:new FormControl('',[Validators.required]),
          roadName:new FormControl('',[Validators.required]),
          city:new FormControl('',[Validators.required]),
          pincode:new FormControl('',[Validators.required])
    })
  }
  addFav(items:any){
    console.log("Fav List",items);
    const favorites = this.RegistryForm.controls['favorites'] as FormArray;
    this.tempArray2 = items;
    favorites.push(new FormControl(this.tempArray2))
    
  }
  navToHome(){
    this.router.navigateByUrl('');
  }
  ownerLogin(){
    this.router.navigateByUrl('ownerlogin')
  }


}
