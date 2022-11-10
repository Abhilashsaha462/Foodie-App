import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { OwnerProfileComponent } from 'src/app/owner-profile/owner-profile.component';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { RestaurantEditComponent } from 'src/app/Restaurants/restaurant-add/restaurant-edit.component';
import { LinkService } from 'src/app/Services/link.service';
import { EditRestaurantComponent } from '../edit-restaurant/edit-restaurant.component';

@Component({
  selector: 'app-ownerdata',
  templateUrl: './ownerdata.component.html',
  styleUrls: ['./ownerdata.component.css']
})
export class OwnerdataComponent implements OnInit {

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
    this.link.ownerLogOut();
    console.log('Token after Out',this.link.getOwnerToken());
    window.location.href="/ownerlogin";
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
    const dialogRef = this.dialog.open(RestaurantEditComponent, {
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
