import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { LinkService } from '../Services/link.service';

@Component({
  selector: 'app-owner-profile',
  templateUrl: './owner-profile.component.html',
  styleUrls: ['./owner-profile.component.css']
})
export class OwnerProfileComponent implements OnInit {
  EditForm : FormGroup;
  items!:FormArray;
  constructor(private formbuilder:FormBuilder,public dialogRef: MatDialogRef<OwnerProfileComponent>,private link:LinkService,
    private avtivate:ActivatedRoute,private router:Router) {
    this.EditForm=this.formbuilder.group({
      userName:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required,Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,30}$/)]),
      phoneNo:new FormControl('',[Validators.required]),
      address:this.formbuilder.array([
        this.formbuilder.group({
          homeNo:new FormControl('',[Validators.required]),
          roadName:new FormControl('',[Validators.required]),
          city:new FormControl('',[Validators.required]),
          pincode:new FormControl('',[Validators.required])
        })
      ]),
      favorites:this.formbuilder.array([
        this.formbuilder.group({
          restId:new FormControl('',[Validators.required]),
          restName:new FormControl('',[Validators.required]),
          city:new FormControl('',[Validators.required])
        })
      ])
    })
   }

  ngOnInit(): void {
    this.avtivate.paramMap.subscribe(x=>{
       let id = this.link.getEmail();
      this.link.getUserForUpdate().subscribe(data=>{
        this.EditForm.setValue(data);
      })
    })
  }
  email1:any;
  data1:any;
  updateData(){
    this.addAddress();
    const regData = this.EditForm.value;
    this.email1 = this.link.getEmail();
    if(this.email1==regData.email){
      this.link.updateUser(regData).subscribe((x)=>{
        this.data1=x;
        
        Swal.fire("Your Changes Updated Successfully...!!!")
        console.log("Updated data",this.data1);
      //  window.location.href="/restaurants-details";
      this.router.navigateByUrl("/restaurants-details")
        
      })
    }else{
      
      Swal.fire("Invalid Email...!!!","Try Again Later...!!!","error")
    }
  }
  get add() {
    return this.EditForm.get('address') as FormArray;
  }
  addAddress(){
    console.log("Address data",this.items);
    this.items = this.EditForm.get('address') as FormArray;
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

  cancelation(): void {
    window.location.href="/restaurants-details";
    // this.dialogRef.close();
  }

}
