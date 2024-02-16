import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

import { environment } from 'src/environments/environment';
import { AppConstants } from '../constants/app.constants';
import { User } from '../model/user.model';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
    
  }

  validateLoginDetails(user: User) {
    window.sessionStorage.setItem("userdetails", JSON.stringify(user));
    return this.http.get(environment.rooturl1 + AppConstants.LOGIN_API_URL, { observe: 'response', withCredentials: true });
  }

}
