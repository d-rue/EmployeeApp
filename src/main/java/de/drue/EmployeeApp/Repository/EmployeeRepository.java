package de.drue.EmployeeApp.Repository;

import de.drue.EmployeeApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstName(String firstname);
    List<Employee> findByLastName(String lastname);
    List<Employee> findByUserName(String username);
    List<Employee> findByTitle(String title);
    List<Employee> findByBirthday(Date date);
    List<Employee> findByPhoneNumber(String phone);
    List<Employee> findByFirstNameContaining(String string);
    List<Employee> findByFirstNameNotContaining(String string);
    List<Employee> findByFirstNameStartsWith(String string);
    List<Employee> findByFirstNameEndsWith(String string);
    @Query("SELECT e FROM Employee e WHERE e.firstName=:firstname AND e.lastName=:lastname")
    List<Employee> findByFirstNameAndLastName(String firstname, String lastname);
    @Query("SELECT e FROM Employee e WHERE e.firstName=:firstname AND e.lastName=:lastname AND e.userName=:username")
    List<Employee> findByFirstNameAndLastNameAndUserName(String firstname, String lastname, String username);
    @Query("SELECT e FROM Employee e WHERE e.userName=:username AND e.title=:title")
    List<Employee> findByUserNameAndTitle(String username, String title);
    @Query("SELECT e FROM Employee e WHERE e.userName=:username AND e.phoneNumber=:phonenumber")
    List<Employee> findByUserNameAndPhoneNumber(String username, String phonenumber);
    @Query("SELECT e FROM Employee e WHERE e.title=:title AND e.phoneNumber=:phonenumber")
    List<Employee> findByTitleAndPhoneNumber(String title, String phonenumber);
}
