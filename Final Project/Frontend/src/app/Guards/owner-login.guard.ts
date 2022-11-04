import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LinkService } from '../Services/link.service';

@Injectable({
  providedIn: 'root'
})
export class OwnerLoginGuard implements CanActivate {
  constructor(private login:LinkService,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.login.isOwnerLoggedIn()){
        return true;
      }else{
        alert("You Need To Log in first...!!!")
        this.router.navigate(['ownerlogin'])
        return false;
      }
  }
}
