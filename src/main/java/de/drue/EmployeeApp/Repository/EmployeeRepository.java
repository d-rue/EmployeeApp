package de.drue.EmployeeApp.Repository;

import de.drue.EmployeeApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstName(String firstname);
    List<Employee> findByLastName(String lastname);
    List<Employee> findByUserName(String username);
    List<Employee> findByFirstNameContaining(String string);
    List<Employee> findByFirstNameNotContaining(String string);
    List<Employee> findByFirstNameStartsWith(String string);
    List<Employee> findByFirstNameEndsWith(String string);
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstname AND e.lastName = :lastname")
    List<Employee> findByFirstNameAndLastName(String firstname, String lastname);
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstname AND e.lastName = :lastname AND e.userName = :username")
    List<Employee> findByFirstNameAndLastNameAndUserName(String firstname, String lastname, String username);
    @Query("SELECT e FROM Employee e JOIN e.addresses a WHERE a.city = :city")
    List<Employee> findByAddressCity(String city);
    @Query("SELECT e FROM Employee e JOIN e.courses c WHERE c.title = :title")
    List<Employee> findByCourseTitle(String title);
}
