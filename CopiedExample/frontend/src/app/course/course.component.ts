import {Component, OnInit} from '@angular/core';
import {HttpService} from "../service/http.service";
import {ActivatedRoute} from "@angular/router";
import {Course} from "../model/course.model";
import {CoursePlan} from "../model/courseplan.model";
import {RegisteruserModel} from "../model/registeruser.model";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  id: string = ""
  course?: Course
  coursePlans: CoursePlan[] = []
  selectedDate?: Date;
  firstname: string = "";
  lastname: string = "";

  constructor(private route: ActivatedRoute, private http: HttpService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params["id"] == null ? "" : params["id"];
    });

    this.http.getCouseById(this.id).subscribe((c => {
      this.course = c;
      console.log(c)
    }))

    this.http.getCoursePlanById(this.id).subscribe((c => {
      this.coursePlans = c
      console.log(c)
    }))

  }


  register() {
    this.coursePlans.forEach(plan=>{
      if (plan.start == this.selectedDate){
        const register : RegisteruserModel = {
          firstname: this.firstname,
          lastname: this.lastname,
          planId: plan.id
        }
        this.http.registerPerson(register).subscribe((c=>{
          console.log(c)
        }))
      }
    })
  }
}
