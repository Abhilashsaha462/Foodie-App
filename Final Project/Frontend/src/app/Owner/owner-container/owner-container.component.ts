import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-owner-container',
  templateUrl: './owner-container.component.html',
  styleUrls: ['./owner-container.component.css']
})
export class OwnerContainerComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  navToHome(){
    this.router.navigateByUrl('/home');
  }
}
