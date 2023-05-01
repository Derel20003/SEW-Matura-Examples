import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SearchComponent} from './search/search.component';
import {CourseComponent} from './course/course.component';
import {RegisteredComponent} from './registered/registered.component';
import {HttpClientModule} from "@angular/common/http";
import {Route, RouterModule} from "@angular/router";

const routes: Route[] = [
  {path: 'search', component: SearchComponent},
  {path: 'course/:text', component: CourseComponent},
  {path: '**', redirectTo: 'search'}
]

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    CourseComponent,
    RegisteredComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
