import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LinkService } from '../Services/link.service';

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
    // this.login.toLoggedOut();
    this.login.isLoggedIn();
    this.login.getFav().subscribe(x=>{
      console.log(x)
    });
  }

  data:any;

  loginFun(){
    const regData = this.LoginForm.value;
    console.log("RegData",regData);
    
    //if(regData.email!='' && regData.password!=''){
    this.login.check(regData).subscribe((x:any)=>{
      this.data=x;
      console.log("OK",this.data);
      this.login.loginUser(x.token);
      console.log("After");
      
      this.login.isLoggedIn();
        alert("Login Success..!!!");
          window.location.href="/restaurants-details";
        this.login.setEmail(regData.email);
        console.log("All data",regData)
        return this.login.isLogin=true;
    },
    error=>{
      alert("Incorrect Email Or Password...!!! Try Again...!!!")
    });
    //}
  }

  navToHome(){
    this.router.navigateByUrl('');
  }

  navToRegisterComp(){
    this.router.navigateByUrl('/container/register');
  }

}
