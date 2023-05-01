export interface Course {
  id: string;
  title: string;
  description?: string;

  prerequisite?: Course;
}
