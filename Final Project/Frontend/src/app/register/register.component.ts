import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Address } from '../Model-Classes/address';
import { Restaurant } from '../Model-Classes/Restaurant';
import { LinkService } from '../Services/link.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  RegistryForm:FormGroup;
  tempArray1:Address | undefined;
  tempArray2:Restaurant|undefined;
  items!:FormArray;
  constructor(private register:LinkService,private formbuilder:FormBuilder,private router:Router) {
    this.RegistryForm=this.formbuilder.group({
      userName:new FormControl('',[Validators.required,Validators.minLength(5)]),
      email:new FormControl('',[Validators.required,Validators.email]),
      password:new FormControl('',[Validators.required,Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,30}$/)]),
      phoneNo:new FormControl('',[Validators.required,Validators.pattern(/^\+?\d{1,4}?[-.\s]?\(?\d{1,3}?\)?[-.\s]?\d{1,4}[-.\s]?\d{1,4}[-.\s]?\d{1,9}$/)]),
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
      
      const regData = this.RegistryForm.value;
      this.addAddress();
      console.log(regData);
      if(regData.email!=''||regData.password!=''||regData.userName!=''){
    this.register.store(regData).subscribe(() => {
      Swal.fire("Congratulations...!!!","Registered Successfully...!!!","success");
      this.router.navigateByUrl("/login")
    });
    }else{
      Swal.fire("Email or Password should Not Blank","Registration Failed...!!!","error");
      location.reload();
    }
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

}
