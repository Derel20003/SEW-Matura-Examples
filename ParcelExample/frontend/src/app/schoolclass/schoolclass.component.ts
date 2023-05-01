import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {HttpService} from "../service/http.service";
import {SchoolclassModel} from "../model/schoolclass.mode";

@Component({
  selector: 'app-schoolclass',
  templateUrl: './schoolclass.component.html',
  styleUrls: ['./schoolclass.component.css']
})
export class SchoolclassComponent {
  addressForm = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
  });

  schoolclass?: SchoolclassModel;


  constructor(private fb: FormBuilder, private http: HttpService) {}

  onSubmit(): void {

    // @ts-ignore
    this.schoolclass = this.addressForm.getRawValue();
    console.log(this.schoolclass)
    this.http.addScoolClass(this.schoolclass!).subscribe((c=>{
      console.log("succes")
    }))
  }
}
