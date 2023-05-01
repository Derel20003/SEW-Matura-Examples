import {Person} from "./person.model";

export interface CoursePlan {
  id: number;
  start: Date;
  end: Date;
  location: String;
  teacher: Person;
}
