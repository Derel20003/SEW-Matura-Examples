import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./customer/customer.component";
import {ReservationListComponent} from "./reservation-list/reservation-list.component";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {CreateReservationComponent} from "./create-reservation/create-reservation.component";
import {AuthGuard} from "./guard/auth.guard";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  { path: '', component: CustomerComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'customer', component: CustomerComponent, canActivate: [AuthGuard] },
  { path: 'res-list', component: ReservationListComponent, canActivate: [AuthGuard] },
  { path: 'create-res', component: CreateReservationComponent, canActivate: [AuthGuard] },
  //{ path: 'reservation', component: ReservationComponent }
  { path: '**', component: ErrorPageComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
