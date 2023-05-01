import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SchoolclassModel} from "../model/schoolclass.mode";
import {StudentModel} from "../model/student.model";

const URL = "http://localhost:8080/api"

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) {
  }


  addScoolClass(data: SchoolclassModel): Observable<any> {
    return this.http.post(URL + "/class/new", {title: data.title, description: data.description})
  }

  getallClasses(): Observable<SchoolclassModel[]> {
    return this.http.get<SchoolclassModel[]>(URL + "/class/all");
  }

  addStudent(data: StudentModel): Observable<any> {
    return this.http.post(URL + "/student/new", data)
  }


  putStudentTOClas(student: StudentModel, zweig: String, classid: number | undefined): Observable<any> {


    return this.http.put(URL + "/class/addStudent/" + zweig, {
      firstname: student.firstname,
      lastname: student.lastname,
      classId: classid
    })
  }

  getStudentsPerClassname(classname: String): Observable<StudentModel[]> {
    return this.http.get<StudentModel[]>(URL + "/student/" + classname);
  }

  deleteStudents(student: StudentModel): Observable<any> {
    return this.http.delete(URL + "/student/delete", {body: student});
  }
}
