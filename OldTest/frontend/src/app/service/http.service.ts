import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Course} from "../model/course.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  BASE_URL: String = 'http://localhost:8080/api/'

  constructor(private http: HttpClient) {}

  load(text: String): Observable<Course[]> {
    return this.http.get<Course[]>(this.BASE_URL + 'course/search/' + text)
  }

  loadId(text: String): Observable<Course> {
    return this.http.get<Course>(this.BASE_URL + 'course/search/' + text)
  }
}
