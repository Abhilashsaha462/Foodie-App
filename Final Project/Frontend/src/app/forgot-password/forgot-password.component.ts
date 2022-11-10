import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { LinkService } from '../Services/link.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  ForgotForm:FormGroup;
  constructor(private link:LinkService,private router:Router) {
    this.ForgotForm = new FormGroup({
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      userName:new FormControl('',[Validators.required])
    })
   }

  ngOnInit(): void {
  }
  data0:any;
  retriewPassword(){
    this.data0 = this.ForgotForm.value;
    this.link.forgotPassword(this.data0).subscribe((x)=>{
      console.log(x);
      Swal.fire("Done","Password Reset Successfully", "success");
      this.router.navigateByUrl("/login")
      
    },(error)=>{
      Swal.fire("Failed...","User Name Or Email is Incorrect...!!!","error");
    }
    
    )
  }

}
