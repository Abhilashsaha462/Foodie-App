import { HttpParams } from '@angular/common/http';
import { Component, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { OwnerProfileComponent } from '../owner-profile/owner-profile.component';
import { Restaurant } from '../Model-Classes/Restaurant';
import { DisplayRestaurantComponent } from '../Restaurants/display-restaurant/display-restaurant.component';
import { RestaurantEditComponent } from '../Restaurants/restaurant-add/restaurant-edit.component';
import { LinkService } from '../Services/link.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  thumbnail: any;
  RestForm:FormGroup;
    constructor(private router:Router, public dialog: MatDialog,
      private link:LinkService,private formbuilder:FormBuilder,private sanitizer:DomSanitizer) { 
      this.RestForm = this.formbuilder.group({
        restId: new FormControl('',[Validators.required]),
        restName: new FormControl('',[Validators.required]),
        city:new FormControl('',[Validators.required]),
        url:new FormControl('',[Validators.required]),
        menuList: this.formbuilder.array([
          this.formbuilder.group({
            itemName: new FormControl('',[Validators.required]),
            price: new FormControl('',[Validators.required])
          })
        ])
      })
    }
  
    ngOnInit(): void {
      this. getFav1();
      this.getAll();
    }
    restaurant:Restaurant[]=[];
    restaurant1:Restaurant[]=[];
    k:any[]=[];
    getAll(){
      this.link.getAll().subscribe((x)=>{
        this.k = x;
        console.log("k",this.k);
       
      })
    }
    logout(){
  
      // logout code
  
      this.router.navigateByUrl('');
    }
  
    //  only test
  
    openProfile() : void{
      
      //  get profile code
  
      const dialogRef = this.dialog.open(OwnerProfileComponent, {
        width : '45%',
        height : '600px'
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The Dialog was closed!');
      })
    }
  
    openRestaurantsDetails(){
      const dialogRef = this.dialog.open(RestaurantEditComponent, {
        width : '55%',
        height : '650px'
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The Dialog was closed!');
      })
    }
    openRestaurant(restName:any){
      const dialogRef = this.dialog.open(DisplayRestaurantComponent, {
        width : '55%',
        height : '550px'
      });
      localStorage.setItem("restName",restName)
      dialogRef.afterClosed().subscribe(result => {
        console.log('The Dialog was closed!');
      })
    }
   
  
    
    favorite=false;
  addToFav(pass:any){
    this.RestForm.get('restId')?.setValue(pass.restId);
    this.RestForm.get('restName')?.setValue(pass.restName);
    this.RestForm.get('city')?.setValue(pass.city);
    console.log("pass",pass.restId);
    //this.RestForm.setValue(pass);
    let imageData = 'data:image/jpeg;base64,' +pass.image;
    this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(imageData);
    Swal.fire("Congrats...!!!","Restaurant Added to Favorite List...","success");
    
    this.link.addFav(this.RestForm.value).subscribe((x)=>{
      
      console.log("List Added",x);
       this.getFav1();
    })
  }
  deleteFav(pass:any){
    //this.RestForm.get('restId')?.setValue(pass.restId);
    console.log("Delete",pass);
    
  this.link.removeFav(pass).subscribe((x)=>{
  console.log("List Removed from Fav...");
  this.getFav1();
  })
  }
  tempArray:string='';
  temp:number[]=[];

  getFav1(){
    this.link.getFav().subscribe((x)=>{
      console.log("Fav list is ",x);
      this.restaurant=x;
      this.temp=this.restaurant.map(x=>x.restId);
      console.log("J",this.temp);
      this.tempArray = JSON.stringify(this.temp);
      console.log("tempArray",this.tempArray);
      this.link.getmyFav(this.temp).subscribe((x)=>{
        this.restaurant1=x;
        console.log("my",x);
        
      })
     
  });
    
  }
  toFavList(){
    this.router.navigateByUrl('/favorites');
  }
  editRest(){
    this.router.navigateByUrl('/editrestaurant');
  }
  dashProfile(){
    this.router.navigateByUrl('/restaurants-details');
  }
 

}
