import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Menu } from 'src/app/Model-Classes/Menu';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { LinkService } from 'src/app/Services/link.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.css']
})
export class EditRestaurantComponent implements OnInit {
RestForm:FormGroup;
public userFile:any=File;
items!:FormArray;
  constructor(private link:LinkService,private activate:ActivatedRoute,private formbuilder:FormBuilder,
    public dailog: MatDialogRef<EditRestaurantComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.RestForm = this.formbuilder.group({
      restId: new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required]),
      restName: new FormControl('',[Validators.required]),
      city:new FormControl('',[Validators.required]),
     // url:new FormControl('',[]),
      menuList: this.formbuilder.array([
        this.formbuilder.group({
          itemName: new FormControl('',[Validators.required]),
          price: new FormControl('',[Validators.required])
        })
      ])
    })
   }
  getRestaurantData(){

  }
  list:Menu[]=[];
restaurant:Restaurant=new Restaurant();
  ngOnInit(): void {
    // this.activate.paramMap.subscribe((x)=>{
    //   let k = localStorage.getItem('restName');
    //   this.link.getRestaurant(k).subscribe((y)=>{
    //     console.log("y",y);
       // this.list=y.menuList;
        this.RestForm.patchValue(this.data);
    //     this.restaurant = y;
        
      
        
    //   })
    // })
    // this.RestForm.get('restId')?.setValue(this.data.restId);
    // this.RestForm.get('restName')?.setValue(this.data.restName);
    // this.RestForm.get('email')?.setValue(this.data.email);
    // this.RestForm.get('city')?.setValue(this.data.city);
    // this.RestForm.get('menuList')?.setValue(this.data.menuList);
    //this.RestForm.get('menuList.price')?.setValue(this.data.menuList.price);
  }
  onSelectFile(event){
    const f = event.target.files[0];
    console.log("image",f);
 
    
    this.userFile = f;
    
  }
  saveData(){
    
    const u = this.RestForm.value;
    
     this.userFile = this.restaurant.url;
     console.log(this.userFile);
    var formData = new FormData();
    formData.append('details', JSON.stringify(u))
    formData.append('file',this.userFile);
    
    this.link.updateRestaurant(u).subscribe((response)=>{
      console.log(response);
      Swal.fire("Data Saved Successfully...!!!")
    })

  }
  menuList:any;
  get add() {
    
    return this.RestForm.get('menuList') as FormArray;
  }
  Removeitem(index:any){
    this.items = this.RestForm.get("menuList") as FormArray;
    this.items.removeAt(index)
  }
  addNewRow(){
    this.menuList = this.RestForm.get("menuList") as FormArray;
    this.menuList.push(this.Genrow())
  }
  Genrow(): FormGroup {
    return new FormGroup({
     itemName:new FormControl('',Validators.required),
     price:new FormControl('',Validators.required)
    });
  }
  cancelation(){

  }

}
