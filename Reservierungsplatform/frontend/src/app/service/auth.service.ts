import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  SERVER_URL = 'http://localhost:8080/api/auth'

  constructor(private http: HttpClient,
              private router: Router) { }

  login(userid: string, password: string): Observable<any> {
    return this.http.post(this.SERVER_URL + '/login', {userid, password})
  }

  isLoggedIn(): boolean {
    const n = new Date().getTime() / 1000
    const exp = Number(localStorage.getItem('expires_at'))
    return n < exp
  }

  logout(): void {
    localStorage.removeItem('id_token')
    localStorage.removeItem('expires_at')
  }
}
