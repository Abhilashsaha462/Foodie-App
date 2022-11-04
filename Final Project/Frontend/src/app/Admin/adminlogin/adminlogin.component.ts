import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LinkService } from 'src/app/Services/link.service';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {

  LoginForm:FormGroup;
  constructor(private login:LinkService,private router:Router) { 
    this.LoginForm = new FormGroup({
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
    })
  }

  ngOnInit(): void {
    // this.login.toLoggedOut();
    this.login.isLoggedIn();
     this.login.getFav().subscribe(x=>{console.log(x)}
     );
  }
  data:any;
  loginFun(){
   const regData = this.LoginForm.value;
   console.log("RegData",regData);
   
   if(regData.email!='' && regData.password!=''){
    this.login.adminLog(regData).subscribe((x:any)=>{
      this.data=x;
         alert("Login Success..!!!");
          window.location.href="/admindash";
         this.login.setEmail(regData.email);
         console.log("All data",regData)
         return this.login.isLogin=true;
    })
    // },error=>{
    //   alert("Incorrect Email Or Password...!!! Try Again...!!!")
    // })
   }
  }
  navToHome(){
    this.router.navigateByUrl('');
  }

  navToRegisterComp(){
    this.router.navigateByUrl('/container/register');
  }


}
