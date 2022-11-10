import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { reg } from '../Model-Classes/reg';
import { Restaurant } from '../Model-Classes/Restaurant';

@Injectable({
  providedIn: 'root'
})
export class LinkService {
isLogin=false;
data2:any;
restaurants:Restaurant[]=[];
private restaurants$ = new Subject<Restaurant[]>();
  constructor(private httpclient:HttpClient) { }
  url="http://localhost:9000/api/v3/login"
  url1="http://localhost:9000/user-service/user/favorite/get/";
  url2="http://localhost:9000/api/v2/all";
  url3="http://localhost:9000/api/v2/add";
  url4="http://localhost:9000/user-service/user/update";
  url5="http://localhost:9000/user-service/get";
  url6="http://localhost:9000/api/v2"
  url7="http://localhost:9000/order-services"
  url8="http://localhost:9000/user-service/user/favorite/delete"
  url9="http://localhost:9000/user-service/user/favorite/add";
  url10="http://localhost:9000/order-services/get";
  url11="http://localhost:9000/api/v2/getRest";
  url12="http://localhost:9000/api/v2/search";
  url13="http://localhost:9000/admin-service/login";
  url14="http://localhost:9000/user-service/getAll";
  url15="http://localhost:9000/user-service/remove";
  url16="http://localhost:9000/api/v2/deleterestaurant";
  url17="http://localhost:9000/api/v2/ownerRegister";
  url18="http://localhost:9000/api/v2/ownerLogin";
  url19="http://localhost:9000/api/v2/ownerRest";
  url20="http://localhost:9000/api/v2/rest/update";
  url21="http://localhost:9000/api/v3/forgot";
  url22="http://localhost:9000/order-services/order/delete";
  store(regData:reg): Observable<reg>{
    return this.httpclient.post<reg>('http://localhost:9000/user-service/user/register', regData,{responseType:'text' as 'json'});
  }
  adminLog(data:any):Observable<any>{
    return this.httpclient.post<any>(`${this.url13}`,data);
  }
  getFav():Observable<any>{
    this.data2 = this.getEmail();
    return this.httpclient.get<any>(`${this.url1}/${this.data2}`)
  }
  addFav(rest:Restaurant):Observable<any>{
    this.data2 = this.getEmail();
    return this.httpclient.put<Restaurant>(`${this.url9}/${this.data2}`,rest)
  }
  removeFav(id:number):Observable<any>{
    this.data2 = this.getEmail();
    return this.httpclient.delete<any>(`${this.url8}/${this.data2}/${id}`)
  }
  getAll(){
    return this.httpclient.get<Restaurant[]>(`${this.url2}`)
  }
  loginUser(Token: string){
    localStorage.setItem("Token",Token)
    return true;
  }
  //to check user log in
  isLoggedIn(){
    let token = localStorage.getItem("Token")
    if(token===undefined || token==='' || token==null){
      console.log("Logged Out");
      
      return false;
    }else{
      console.log("Token",token);
      
      console.log("Logged In");
      
      return true;
    }
  }
  //to logout user
  toLoggedOut(){
    localStorage.removeItem("Token")
    return true;
  }
  //to get token
  getToken(){
    return localStorage.getItem("Token");
  }
  setOwnerToken(tok:string){
    localStorage.setItem('Owner Token',tok);
    return true;
  }
  getOwnerToken(){
    return localStorage.getItem('Owner Token');
  }
  ownerLogOut(){
    localStorage.removeItem('Owner Token');
  }
  isOwnerLoggedIn(){
    let token = localStorage.getItem("Owner Token")
    if(token===undefined || token==='' || token==null){
      console.log("Logged Out");
      
      return false;
    }else{
      console.log("Token",token);
      
      console.log("Logged In");
      
      return true;
    }
  }
  setAdminToken(adminT:string){
    localStorage.setItem('Admin token',adminT)
  }
  getAdminToken(){
    return localStorage.getItem('Admin token');
  }
  isAdminLoggedIn(){
    let token = localStorage.getItem("Admin token")
    if(token===undefined || token==='' || token==null){
      console.log("Logged Out");
      
      return false;
    }else{
      console.log("Token",token);
      
      console.log("Logged In");
      
      return true;
    }
  }
  adminLoggedOut(){
    localStorage.removeItem('Admin token')
  }
  check(regData:reg):Observable<reg> {
   
    return this.httpclient.post<reg>(`${this.url}`,regData);
  }
  setEmail(email:string){
    localStorage.setItem("Email",email);
    return true;
 }
 getEmail(){
   return localStorage.getItem("Email")
 }
 upload(data:FormData):Observable<any>{
 return this.httpclient.post<any>(`${this.url3}`,data)
 }
 updateUser(data:reg):Observable<any>{
   console.log("user",data);
   
  return this.httpclient.put<any>(`${this.url4}`,data)
 }
 getUserForUpdate():Observable<any>{
  this.data2 = this.getEmail();
  return this.httpclient.get<any>(`${this.url5}/${this.data2}`)
 }
 getRestaurant(data:any):Observable<any>{
   console.log("inside",data);
   
   return this.httpclient.get<any>(`${this.url6}/${data}`)
 }
 placeOrder(data:any):Observable<any>{
   this.data2 = this.getEmail();
   return this.httpclient.post<any>(`${this.url7}/${this.data2}`,data)
 }
 getOrder(data0:any):Observable<any>{
  return this.httpclient.get<any>(`${this.url10}/${data0}`)
 }
 getmyFav(data1:any):Observable<any>{
  return this.httpclient.get<any>(`${this.url11}/${data1}`);
 }
 getSearch(data:any):Observable<any>{
   return this.httpclient.get<any>(`${this.url12}/${data}`);
 }
 getallUser():Observable<any>{
   return this.httpclient.get<any>(`${this.url14}`);
 }
 deleteUser(data:any):Observable<any>{
  return this.httpclient.delete<any>(`${this.url15}/${data}`)
 }
 deleteRest(data:any):Observable<any>{
  return this.httpclient.delete<any>(`${this.url16}/${data}`);
 }
 ownerRegister(data:any):Observable<any>{
  return this.httpclient.post<any>(`${this.url17}`,data,{responseType:'text' as 'json'});
 }
 ownerLogin(data:any):Observable<any>{
   return this.httpclient.post<any>(`${this.url18}`,data)
 }
 getOwnerRest(data:any):Observable<any>{
   return this.httpclient.get<any>(`${this.url19}/${data}`);
 }
 updateRestaurant(data:any):Observable<any>{
  return this.httpclient.put<any>(`${this.url20}`,data);
 }
 forgotPassword(data:any):Observable<any>{
  return this.httpclient.put<any>(`${this.url21}`,data);
 }
 deleteOrder(data:any):Observable<any>{
  return this.httpclient.delete<any>(`${this.url22}/${data}`);
 }
}
