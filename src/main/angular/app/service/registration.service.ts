import {Injectable} from '@angular/core';
import {User} from "../model/user.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

const BASE_URI = "./api/registrations";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) {
  }

  register(user: User): Observable<void> {
    return this.http.post<void>(BASE_URI, user);
  }
}
