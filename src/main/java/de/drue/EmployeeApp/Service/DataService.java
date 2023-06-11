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

    public List<Employee> getEmployeeDataFindAll() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeDataFindById(final Long id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " not found")));
    }
    public List<Employee> getEmployeeDataFindByFirstName(final String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
    public List<Employee> getEmployeeDataFindByLastName(final String lastName) {
        return employeeRepository.findByLastName(lastName);
    }
    public List<Employee> getEmployeeDataFindByUserName(final String userName) {
        return employeeRepository.findByUserName(userName);
    }
    public List<Employee> getEmployeeDataFindByFirstNameContaining(final String string) {
        return employeeRepository.findByFirstNameContaining(string);
    }
    public List<Employee> getEmployeeDataFindByFirstNameNotContaining(final String string) {
        return employeeRepository.findByFirstNameNotContaining(string);
    }
    public List<Employee> getEmployeeDataFindByFirstNameStartsWith(final String string) {
        return employeeRepository.findByFirstNameStartsWith(string);
    }
    public List<Employee> getEmployeeDataFindByFirstNameEndsWith(final String string) {
        return employeeRepository.findByFirstNameEndsWith(string);
    }
    public List<Employee> getEmployeeDataFindByFirstNameAndLastName(final String firstname, final String lastname) {
        return employeeRepository.findByFirstNameAndLastName(firstname, lastname);
    }
    public List<Employee> getEmployeeDataFindByFirstNameAndLastNameAndUserName(final String firstname, final String lastname, final String username) {
        return employeeRepository.findByFirstNameAndLastNameAndUserName(firstname, lastname, username);
    }
    public List<Employee> getEmployeeDataFindByAddressCity(final String city) {
        return employeeRepository.findByAddressCity(city);
    }
    public List<Employee> getEmployeeDataFindByCourseTitle(final String title) {
        return employeeRepository.findByCourseTitle(title);
    }
}
