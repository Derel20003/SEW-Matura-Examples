import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpService} from "../service/http.service";
import {Course} from "../model/course.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  @ViewChild('courseSearch', {static: false})
  courseSearch!: ElementRef;

  courses: Course[] = []

  courseSearchVal: String = ''

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
  }

  search(): void {
    const text: String = this.courseSearchVal
    this.httpService.load(text).subscribe(courses => {
      console.log(courses)
      this.courses = courses
    })
  }

  protected readonly console = console;
}
