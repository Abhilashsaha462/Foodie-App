import { SelectorMatcher } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FavoriteComponent } from 'src/app/favorite/favorite.component';
import { OwnerProfileComponent } from '../../owner-profile/owner-profile.component';
import { Restaurant } from '../../Model-Classes/Restaurant';
import { LinkService } from '../../Services/link.service';
import { DisplayRestaurantComponent } from '../display-restaurant/display-restaurant.component';
import { RestaurantEditComponent } from '../restaurant-add/restaurant-edit.component';
declare function search():void;
declare var $: any;
declare var jQuery: any;

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {
  thumbnail: any;
RestForm:FormGroup;
  constructor(private router:Router, public dialog: MatDialog,private link:LinkService,private formbuilder:FormBuilder,private sanitizer:DomSanitizer) { 
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
    });
    
  }

  ngOnInit(): void {
    this.getAll();
  }
  restaurant:Restaurant[]=[];
  getAll(){
    this.link.getAll().subscribe((x)=>{
      this.restaurant=x;
      console.log("All Data...!!!",this.restaurant);
      
    })
  }
  logout(){

    // logout code
    this.link.toLoggedOut();
    this.router.navigateByUrl('');
  }

  //  only test

  openProfile() : void{
    
    //  get profile code

    const dialogRef = this.dialog.open(OwnerProfileComponent, {
      width : '45%',
      height : '600px'
    });

    // dialogRef.afterClosed().subscribe(result => {
    //   console.log('The Dialog was closed!');
    // })
    
  }

  openRestaurantsDetails(){
    const dialogRef = this.dialog.open(RestaurantEditComponent, {
      width : '75%',
      height : '650px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The Dialog was closed!');
    })
  }
  openRestaurant(restName:any){
    const dialogRef = this.dialog.open(DisplayRestaurantComponent, {
      width : '57%',
      height : '550px'
    });
    localStorage.setItem("restName",restName)
    dialogRef.afterClosed().subscribe(result => {
      console.log('The Dialog was closed!');
    })
  }

  selected: boolean | undefined;
  selectedChange = new EventEmitter<boolean>();
  // public toggleSelected(pass1:any) {
    
  //   this.selected = !this.selected;
  //   this.selectedChange.emit(this.selected);
  //   console.log(pass1);
    
  //   if(this.selected){
  //     console.log("True");
  //     this.addToFav(pass1);
  //     localStorage.setItem('x',pass1.restId);
  //   }else{
  //     console.log("False");
  //     let k = localStorage.getItem('x');
  //     console.log("k",k);
      
  //     this.deleteFav(k);
  //   }
  // }
  favorite=false;
addToFav(pass:any){
  this.RestForm.get('restId')?.setValue(pass.restId);
  this.RestForm.get('restName')?.setValue(pass.restName);
  this.RestForm.get('city')?.setValue(pass.city);
  console.log("pass",pass.restId);
  //this.RestForm.setValue(pass);
  let imageData = 'data:image/jpeg;base64,' +pass.image;
  this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(imageData);
  
  this.link.addFav(this.RestForm.value).subscribe((x)=>{
    console.log("List Added",x);
     this.getFav();
  })
}
deleteFav(pass:any){
  //this.RestForm.get('restId')?.setValue(pass.restId);
  console.log("Delete",pass);
  
this.link.removeFav(pass).subscribe((x)=>{
console.log("List Removed from Fav...");
this.getFav();
})
}
getFav(){
  this.link.getFav().subscribe((x)=>{
    console.log("Fav list is ",x);
    
  })
}
toFavList(){
  //this.router.navigateByUrl('/favorites');
  const dialogRef = this.dialog.open(FavoriteComponent, {
    width : '100%',
    height : '650px'
  });
  
  dialogRef.afterClosed().subscribe(result => {
    console.log('The Dialog was closed!');
  })
}
editRest(){
  this.router.navigateByUrl('/editrestaurant');
}
data0:Restaurant[]=[];
data1:any;
// search(){
//   console.log("Searching...");
//   const l = document.getElementById("search-input") as HTMLInputElement |null;
//   const v = l?.value;
//    let k = v?.charAt(0).toUpperCase().concat(v.slice(1));
//   console.log("k",k);
//   this.data1=k;
//   console.log(v);
//   if(k==''){
//     this.data0=[];
//   }else{
//   this.link.getSearch(k).subscribe((x)=>{
//     console.log("Search Data...",x);
//     this.data0=x;
    
//   });
//   }
//   console.log("Search Data0...",this.data0);
// }
f(){
  new search();
}

}
