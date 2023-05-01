import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SearchComponent} from './search/search.component';
import {CourseComponent} from './course/course.component';
import {RegisteredComponent} from './registered/registered.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ClassTableComponent} from './class-table/class-table.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import {NavComponent} from './nav/nav.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import {SchoolclassComponent} from './schoolclass/schoolclass.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatCardModule} from '@angular/material/card';
import {StudentComponent} from './student/student.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";

let routs: Routes = [
  {path: 'table', component: ClassTableComponent},
  {path: 'student', component: StudentComponent},
  {path: 'class', component: SchoolclassComponent},
  {path: '**', redirectTo: "table", pathMatch: "full"},
];

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    CourseComponent,
    RegisteredComponent,
    ClassTableComponent,
    NavComponent,
    SchoolclassComponent,
    StudentComponent,
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        HttpClientModule,
        RouterModule.forRoot(routs),
        LayoutModule,
        MatToolbarModule,
        MatButtonModule,
        MatSidenavModule,
        MatIconModule,
        MatListModule,
        MatInputModule,
        ReactiveFormsModule,
        MatSelectModule,
        MatRadioModule,
        MatCardModule,
        FormsModule,
        MatButtonToggleModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
