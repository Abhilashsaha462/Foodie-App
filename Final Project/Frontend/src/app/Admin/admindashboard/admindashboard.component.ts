import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/Model-Classes/Restaurant';
import { LinkService } from 'src/app/Services/link.service';
import { User } from 'src/app/Model-Classes/User';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdmindashboardComponent implements OnInit {

  constructor(private link:LinkService) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.getAllRestaurant();
  }
  logout(){
    this.link.adminLoggedOut();
    window.location.href="/adminLog";
  }
  user:User[]=[];
  getAllUsers(){
    this.link.getallUser().subscribe((x)=>{
      this.user=x;
    })

  }
  removeUser(pass:string){
    this.link.deleteUser(pass).subscribe((x)=>{
      console.log("Deleted",x);
      location.reload();
      
    })
  }
  restaurant:Restaurant[]=[];
  
  getAllRestaurant(){
    
      this.link.getAll().subscribe((x)=>{
        this.restaurant=x;
        console.log("All Data...!!!",this.restaurant);
        
      })
    

  }
  removeRestaurant(pass:any){
    this.link.deleteRest(pass).subscribe((x)=>{
      console.log("Restaurant Deleted...",x);
      location.reload();
      
    })
  }
  getAllOerders(){

  }

}
