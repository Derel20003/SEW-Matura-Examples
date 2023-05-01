import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    userid: [null, Validators.required],
    password: [null, Validators.required]
  })

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  login(): void {
    const val = this.loginForm.value
    this.authService.login(val.userid, val.password).subscribe(
      value => {
        localStorage.setItem('id_token', value.token)
        localStorage.setItem('expires_at', value.expires_at)
        this.router.navigateByUrl('/res-list')
      },
      error => {
        this.authService.logout()
      }
    )
  }

}
