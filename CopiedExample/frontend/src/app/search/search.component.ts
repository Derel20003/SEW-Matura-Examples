import {Component, OnInit} from '@angular/core';
import {HttpService} from "../service/http.service";
import {Course} from "../model/course.model";


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchString: string = "";
  courses : Course[] = []

  constructor(private http: HttpService) { }

  ngOnInit(): void {

  }

  search() {
      this.http.search(this.searchString).subscribe((c=>{
       this.courses = c
        console.table(this.courses)
      }))


  }
}
