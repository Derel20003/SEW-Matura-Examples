import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserModel} from "../model/user.model";
import {HttpService} from "../service/http.service";
import {MatRadioButton, MatRadioChange} from "@angular/material/radio";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  addressForm = this.fb.group({
    firstname: [null, Validators.required],
    lastname: [null, Validators.required],
    shipping: ['free', Validators.required]
  });
  selectedLevel: boolean = false

  users: UserModel[] = [];


  constructor(private fb: FormBuilder, http: HttpService) {
    http.getUser().subscribe((c=>{
      this.users = c;
    }))
  }

  onSubmit(): void {
    alert('Thanks!');
  }

  selected($event: boolean, firstname: string) {
    console.log(this.selectedLevel)
    console.log($event)
    console.log(firstname)

    this.users.forEach(c=>{
      if (c.firstname == firstname){

        // @ts-ignore
        this.addressForm.patchValue(c)
        console.log("test")

        console.log(this.addressForm.getRawValue())
        console.log(this.addressForm.value)
      }
    })
  }

  selectedtest() {

  }

  selectedChaned($event: MatRadioChange) {
    console.log(this.selectedLevel , $event)
  }
}
