import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../../Model-Classes/File-handle.model';
import { Restaurant } from '../../Model-Classes/Restaurant';
import { LinkService } from '../../Services/link.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {
  RestForm:FormGroup;
  items!:FormArray;
  imageData:FileHandle[]=[];
  url: FileHandle[]=[];
  public userFile:any=File;
  constructor(private link:LinkService,private formbuilder:FormBuilder,private sanitizer:DomSanitizer) {
    this.RestForm = this.formbuilder.group({
      restId: new FormControl('',[Validators.required]),
      restName: new FormControl('',[Validators.required]),
      city:new FormControl('',[Validators.required]),
      //  url:[],
      menuList: this.formbuilder.array([
        this.formbuilder.group({
          itemName: new FormControl('',[Validators.required]),
          price: new FormControl('',[Validators.required])
        })
      ])
    })
   }

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
  saveData(){
    const u = this.RestForm.value;
    var formData = new FormData();
    formData.append('details', JSON.stringify(u))
    formData.append('file',this.userFile);
    
    this.link.upload(formData).subscribe((response)=>{
      console.log(response);
      
    })
  }
}
