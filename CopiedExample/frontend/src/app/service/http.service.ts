import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "../model/course.model";
import {CoursePlan} from "../model/courseplan.model";
import {RegisteruserModel} from "../model/registeruser.model";
import {UserModel} from "../model/user.model";
const URL = "http://localhost:8080/api/course"
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  search(text: string): Observable<Course[]>{
    return this.http.get<Course[]>(URL + "/search/" + text);
  }

  getCouseById(id:string): Observable<Course>{
    return this.http.get<Course>(URL + "/" + id)
  }

  getCoursePlanById(id:string):Observable<CoursePlan[]>{
    return this.http.get<CoursePlan[]>(URL + "/plan/" + id)
  }

  registerPerson(data:RegisteruserModel):Observable<any>{
    return this.http.post<any>(URL + "/plan",data)
  }
  getUser():Observable<UserModel[]>{
    return this.http.get<UserModel[]>(URL + "/person/all")
  }
}
