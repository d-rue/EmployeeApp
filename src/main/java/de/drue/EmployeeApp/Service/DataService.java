package de.drue.EmployeeApp.Service;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class DataService {
    private Random randomInt = new Random();
    private final EmployeeRepository employeeRepository;
    public DataService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeDatas_FindAll() { return employeeRepository.findAll(); }
    public Optional<Employee> getEmployeeData_FindById(Long id) { return employeeRepository.findById(id); }
    public List<Employee> getEmployeeData_FindByFirstName(String firstName) { return employeeRepository.findByFirstName(firstName); }
    public List<Employee> getEmployeeData_FindByLastName(String lastName) { return employeeRepository.findByLastName(lastName); }
    public List<Employee> getEmployeeData_FindByUserName(String userName) { return employeeRepository.findByUserName(userName); }
    public List<Employee> getEmployeeData_FindByTitle(String title) { return employeeRepository.findByTitle(title); }
    public List<Employee> getEmployeeData_FindByBirthday(Date date) { return employeeRepository.findByBirthday(date); }
    public List<Employee> getEmployeeData_FindByPhoneNumber(String phoneNumber) { return employeeRepository.findByPhoneNumber(phoneNumber); }
    public List<Employee> getEmployeeData_FindByFirstNameContaining(String string) { return employeeRepository.findByFirstNameContaining(string); }
    public List<Employee> getEmployeeData_FindByFirstNameNotContaining(String string) { return employeeRepository.findByFirstNameNotContaining(string); }
    public List<Employee> getEmployeeData_FindByFirstNameStartsWith(String string) { return employeeRepository.findByFirstNameStartsWith(string); }
    public List<Employee> getEmployeeData_FindByFirstNameEndsWith(String string) { return employeeRepository.findByFirstNameEndsWith(string); }
    public List<Employee> getEmployeeData_FindByFirstNameAndLastName(String firstname, String lastname) { return employeeRepository.findByFirstNameAndLastName(firstname, lastname); }
    public List<Employee> getEmployeeData_FindByFirstNameAndLastNameAndUserName(String firstname, String lastname, String username) { return employeeRepository.findByFirstNameAndLastNameAndUserName(firstname, lastname, username); }
    public List<Employee> getEmployeeData_FindByUserNameAndTitle(String username, String title) { return employeeRepository.findByUserNameAndTitle(username, title); }
    public List<Employee> getEmployeeData_FindByUserNameAndPhoneNumber(String username, String phonenumber) { return employeeRepository.findByUserNameAndPhoneNumber(username, phonenumber); }
    public List<Employee> getEmployeeData_FindByTitleAndPhoneNumber(String title, String phonenumber) { return employeeRepository.findByTitleAndPhoneNumber(title, phonenumber); }
}
