package de.drue.EmployeeApp.Repository;

import de.drue.EmployeeApp.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
