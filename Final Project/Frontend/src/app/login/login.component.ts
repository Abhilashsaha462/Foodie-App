import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { LinkService } from '../Services/link.service';
declare var FB: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  LoginForm:FormGroup;
  constructor(private login:LinkService,private router:Router) { 
    this.LoginForm = new FormGroup({
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
    })
  }

  ngOnInit(): void {
    this.login.isLoggedIn();
     this.login.getFav().subscribe(x=>{console.log(x)}
     );

  }
  data:any;
  loginFun(){
   const regData = this.LoginForm.value;
   console.log("RegData",regData);
   
   if(regData.email!='' && regData.password!=''){
    this.login.check(regData).subscribe((x:any)=>{
      this.data=x;
      console.log("OK",this.data);
      this.login.loginUser(x.token);
      
      this.login.isLoggedIn();
      this.login.setEmail(regData.email);
      this.login.isLogin=true;
        Swal.fire("Login Success...!!!");
        
          return window.location.href="/restaurants-details";
    
    },error=>{
      
      Swal.fire("Incorrect Email Or Password...!!!","Try Again...!!!","error");
      
    });
   }else{
    Swal.fire("Email Or Password is missing...!!!","Try Again...!!!","error");
   }
  }
  
  navToHome(){
    this.router.navigateByUrl('');
  }

  navToRegisterComp(){
    this.router.navigateByUrl('/container/register');
  }
  f(){
    new FB();
  }
  submitLogin(){
    window.location.href="/oauth2/authorization/facebook"
  }

}
