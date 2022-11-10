import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from 'src/app/Model-Classes/File-handle.model';
import { Menu } from 'src/app/Model-Classes/Menu';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { LinkService } from 'src/app/Services/link.service';

@Component({
  selector: 'app-restaurant-edit',
  templateUrl: './restaurant-edit.component.html',
  styleUrls: ['./restaurant-edit.component.css']
})
export class RestaurantEditComponent implements OnInit {

  RestForm:FormGroup;
  items!:FormArray;
  imageData:FileHandle[]=[];
  url: FileHandle[]=[];
  public userFile:any=File;
 
  constructor(private link:LinkService,private formbuilder:FormBuilder,private sanitizer:DomSanitizer,
    public dialogRef: MatDialogRef<RestaurantEditComponent>) {
    this.RestForm = this.formbuilder.group({
      restId: new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required]),
      restName: new FormControl('',[Validators.required]),
      city:new FormControl('',[Validators.required]),
      menuList: this.formbuilder.array([
        this.formbuilder.group({
          itemName: new FormControl('',[Validators.required]),
          price: new FormControl('',[Validators.required])
        })
      ])
    })
   }
e:any='';
  ngOnInit(): void {
    
  
    
  }
  
  get add() {
    
    

    return this.RestForm.get('menuList') as FormArray;
  
  }
  onSelectFile(event){
    const f = event.target.files[0];
    console.log("image",f);
    this.userFile = f;
    
  }
  data0:any;
  saveData(){
    console.log(this.RestForm.get('menuList'));
    const u = this.RestForm.value;
    var formData = new FormData();
    formData.append('details', JSON.stringify(u))
    formData.append('file',this.userFile);
    
    this.link.upload(formData).subscribe((response)=>{
      console.log(response);
      
    })
  }
  cancelation(): void {
    this.dialogRef.close();
  }
 
  addNewRow() {
    const menuList = this.RestForm.get("menuList") as FormArray;
    menuList.push(this.Genrow())
  }
  Genrow(): FormGroup {
    return new FormGroup({
     itemName:new FormControl('',Validators.required),
     price:new FormControl('',Validators.required)
    });
  }
  Removeitem(index:any){
    this.items = this.RestForm.get("menuList") as FormArray;
    this.items.removeAt(index)
  }
}

