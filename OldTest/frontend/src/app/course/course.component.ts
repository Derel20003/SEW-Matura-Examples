import {Component, OnInit} from '@angular/core';
import {Course} from "../model/course.model";
import {HttpService} from "../service/http.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  course!: Course

  constructor(private httpService: HttpService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    let text: String = this.route.snapshot.paramMap.get('text')!;
    this.httpService.loadId(text).subscribe(course => {
      //@ts-ignore
      this.course = course[0]
    })
  }
}
