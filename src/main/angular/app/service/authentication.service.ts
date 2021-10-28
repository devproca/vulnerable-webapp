import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicCredentials} from "../model/basic-credentials.model";
import {User} from "../model/user.model";


const BASE_URI = "./api/authentication";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  constructor(private http: HttpClient) {
  }

  login(credentials: BasicCredentials): Observable<User> {
    return this.http.post<User>(`${BASE_URI}/login`, credentials);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${BASE_URI}/currentUser`);
  }
}
