package de.drue.EmployeeApp.Service;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Exception.ResourceNotFoundException;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DataService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployeeDataFindAll() { return employeeRepository.findAll(); }
    public Optional<Employee> getEmployeeDataFindById(Long id) { return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " not found"))); }
    public List<Employee> getEmployeeDataFindByFirstName(String firstName) { return employeeRepository.findByFirstName(firstName); }
    public List<Employee> getEmployeeDataFindByLastName(String lastName) { return employeeRepository.findByLastName(lastName); }
    public List<Employee> getEmployeeDataFindByUserName(String userName) { return employeeRepository.findByUserName(userName); }
    public List<Employee> getEmployeeDataFindByFirstNameContaining(String string) { return employeeRepository.findByFirstNameContaining(string); }
    public List<Employee> getEmployeeDataFindByFirstNameNotContaining(String string) { return employeeRepository.findByFirstNameNotContaining(string); }
    public List<Employee> getEmployeeDataFindByFirstNameStartsWith(String string) { return employeeRepository.findByFirstNameStartsWith(string); }
    public List<Employee> getEmployeeDataFindByFirstNameEndsWith(String string) { return employeeRepository.findByFirstNameEndsWith(string); }
    public List<Employee> getEmployeeDataFindByFirstNameAndLastName(String firstname, String lastname) { return employeeRepository.findByFirstNameAndLastName(firstname, lastname); }
    public List<Employee> getEmployeeDataFindByFirstNameAndLastNameAndUserName(String firstname, String lastname, String username) { return employeeRepository.findByFirstNameAndLastNameAndUserName(firstname, lastname, username); }
    public List<Employee> getEmployeeDataFindByAddressCity(String city) { return employeeRepository.findByAddressCity(city); }
    public List<Employee> getEmployeeDataFindByCourseTitle(String title) { return employeeRepository.findByCourseTitle(title); }
}
