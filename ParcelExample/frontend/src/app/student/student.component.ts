import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {HttpService} from "../service/http.service";
import {SchoolclassModel} from "../model/schoolclass.mode";
import {StudentModel} from "../model/student.model";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  addressForm = this.fb.group({

    firstname: [null, Validators.required],
    lastname: [null, Validators.required],

  });
  classes: SchoolclassModel[] = []
  schoolClass: string = "";
  classRoomCheckbock: boolean = false;
  classid?: number;
  student!: StudentModel
  zweig: String = "";

  constructor(private fb: FormBuilder, private http: HttpService) {
  }

  onSubmit(): void {
    // @ts-ignore
    this.student = this.addressForm.getRawValue();
    console.log(this.student)

    if (!this.classRoomCheckbock) {
      this.http.addStudent(this.student).subscribe((c => {
        console.log("Succes add ")
      }))
    }else{
      this.http.putStudentTOClas(this.student,this.zweig,this.classid).subscribe((c=>{
        console.log(c)
      }))
    }
  }

  ngOnInit(): void {
    this.http.getallClasses().subscribe((c => {
      this.classes = c;
    }))
  }

  selected() {
    this.classes.forEach(c => {
      if (c.title == this.schoolClass) {
        console.log(c.title, c.class_ID)
        this.classid = c.class_ID;
      }
    })
  }

  selectedchanged($event: boolean) {
    this.classRoomCheckbock = $event;


  }


}
