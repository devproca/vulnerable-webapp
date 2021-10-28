import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicCredentials} from "../model/basic-credentials.model";


const BASE_URI = "./api/authentication";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  constructor(private http: HttpClient) {
  }

  login(credentials: BasicCredentials): Observable<void> {
    return this.http.post<void>(`${BASE_URI}/login`, credentials);
  }
}
