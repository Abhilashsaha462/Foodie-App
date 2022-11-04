import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LinkService } from 'src/app/Services/link.service';

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
         //swal.fire("Congratulations...!!!", "You Have Logged In Successfully...!!!", "success");
          window.location.href="/ownerdash";
         this.login.setEmail(regData.email);
         console.log("All data",regData)
         return this.login.isLogin=true;
    })
  
   }
  }
  navToHome(){
    this.router.navigateByUrl('');
  }

  navToRegisterComp(){
    this.router.navigateByUrl('/owner-container/owner-register');
  }


}

function swal(arg0: string, arg1: string, arg2: string) {
  throw new Error('Function not implemented.');
}
