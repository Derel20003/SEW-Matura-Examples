import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {HttpService} from "../service/http.service";
import {SchoolclassModel} from "../model/schoolclass.mode";
import {StudentModel} from "../model/student.model";
import {WebSocketSubject} from "rxjs/internal/observable/dom/WebSocketSubject";
import {webSocket} from "rxjs/webSocket";

@Component({
  selector: 'app-class-table',
  templateUrl: './class-table.component.html',
  styleUrls: ['./class-table.component.css']
})
export class ClassTableComponent implements AfterViewInit, OnInit {
  myWebSocket!: WebSocketSubject<any>; // EMA; WICHTIG!!!!!!!!!!!!!!!!!!!
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  classes: SchoolclassModel[] = []
  schoolClass: string = "";
  dataSource = new MatTableDataSource<StudentModel>();
  students: StudentModel[] = []

  displayedColumns = ['id', 'name', 'select'];

  constructor(private http: HttpService) {
  }

  ngOnInit(): void {
    this.http.getallClasses().subscribe((c => {
      this.classes = c;
    }))

    this.myWebSocket = webSocket({
      url: "ws://localhost:8080/websocket/",
      deserializer: (msg) => msg.data,
    });

    this.myWebSocket.subscribe((value) => {
      let json = JSON.parse(value.toString());
      
      // @ts-ignore
      //this.dataSource.data = changedstudent

      //this.dataSource.data += json

      this.load()
    });

  }


  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;


  }

  selected() {
    this.load()
  }


  load() {
    this.http.getStudentsPerClassname(this.schoolClass).subscribe((c => {
      this.dataSource.data = c;
      console.log(c)
    }))
  }

  deleteStudents() {
    this.students.forEach(c => {
      this.http.deleteStudents(c).subscribe((C => {
        this.load()
      }))
    })
  }

  selectedStudent($event: boolean, firstname: string, lastname: string) {
    if ($event) {
      const student: StudentModel = {
        lastname: lastname,
        firstname: firstname,
      }
      this.students.push(student)
      console.log(this.students)
    } else {
      const student: StudentModel = {
        lastname: lastname,
        firstname: firstname,
      }
      const index = this.students.findIndex(student => student.lastname == lastname && student.firstname == firstname);
      this.students.splice(index, 1);
      console.log(this.students)
    }


  }
}
