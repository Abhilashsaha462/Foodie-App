import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LinkService } from 'src/app/Services/link.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-owner-login',
  templateUrl: './owner-login.component.html',
  styleUrls: ['./owner-login.component.css']
})
export class OwnerLoginComponent implements OnInit {

  LoginForm:FormGroup;
  constructor(private login:LinkService,private router:Router) { 
    this.LoginForm = new FormGroup({
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
    })
  }

  ngOnInit(): void {
  }
  data:any;
  loginFun(){
   const regData = this.LoginForm.value;
   console.log("RegData",regData);
   
   if(regData.email!='' && regData.password!=''){
    this.login.ownerLogin(regData).subscribe((x:any)=>{
      this.data=x;
        //  alert("Login Success..!!!");
         Swal.fire("Congratulations...!!!", "You Have Logged In Successfully...!!!", "success");
         
         this.login.setEmail(regData.email);
         this.login.setOwnerToken('abcd1234');
         console.log("All data",regData);
         console.log('Token is',this.login.getOwnerToken());
         window.location.href="/ownerdash";
         
         return this.login.isLogin=true;
    })
  
   }
  }
  navToHome(){
    this.router.navigateByUrl('');
  }

  navToRegisterComp(){
    this.router.navigateByUrl('ownerregister');
  }


}
function swal(arg0: string, arg1: string, arg2: string) {
  throw new Error('Function not implemented.');
}

