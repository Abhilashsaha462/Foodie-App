import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { OwnerProfileComponent } from 'src/app/owner-profile/owner-profile.component';
import { RestaurantAddComponent } from 'src/app/Restaurants/restaurant-add/restaurant-add.component';
import { LinkService } from 'src/app/Services/link.service';
import { EditRestaurantComponent } from '../edit-restaurant/edit-restaurant.component';

@Component({
  selector: 'app-owner-data',
  templateUrl: './owner-data.component.html',
  styleUrls: ['./owner-data.component.css']
})
export class OwnerDataComponent implements OnInit {

  constructor(private link:LinkService,public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getOwnRestaurants();
  }

  restaurant:Restaurant[]=[];
  
  getOwnRestaurants(){
    let k = localStorage.getItem('Email');
    this.link.getOwnerRest(k).subscribe((x)=>{
      this.restaurant = x;
      console.log("Owners Restaurants",x);
    })
  }
  logout(){

  }
  search(){
    
  }
  openRestaurant(pass:any){
    {
      const dialogRef = this.dialog.open(EditRestaurantComponent, {
        width : '65%',
        height : '650px',
        data:pass
      });
      //localStorage.setItem("card",pass)
      dialogRef.afterClosed().subscribe(result => {
        console.log('The Dialog was closed!');
      })
    }
  }
  editProfile(){
    const dialogRef = this.dialog.open(OwnerProfileComponent, {
      width : '65%',
      height : '650px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The Dialog was closed!');
    })
  }
  addRest(){
    const dialogRef = this.dialog.open(RestaurantAddComponent, {
      width : '65%',
      height : '650px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The Dialog was closed!');
    })
  }
  editRest(){
    const dialogRef = this.dialog.open(EditRestaurantComponent, {
      width : '65%',
      height : '650px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The Dialog was closed!');
    })
  }
}