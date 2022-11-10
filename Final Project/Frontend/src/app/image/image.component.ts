import { Component, Input, OnInit } from '@angular/core';
import { LinkService } from '../Services/link.service';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  constructor(private link:LinkService) { }
  @Input()
  image:string="";
  retriewImage:any;
  ngOnInit(): void {
    this.retriewImage = 'data:image/jpg;base64,' +this.image;
    // console.log(this.image);
    // this.link.getRestaurant(this.image).subscribe(x=>{
    //   this.retriewImage='data:image/jpg;base64,' +x.url
    // })
    
  }

}
