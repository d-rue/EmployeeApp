package de.drue.EmployeeApp.Service;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.github.javafaker.Faker;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DataServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private DataService dataService;
    private Faker faker = new Faker(Locale.GERMAN);

    @Test
    public void DataService_GetEmployeeData_FindAll_ReturnsListOfEmployees(){
        // Arrange
        Employee mockedBookOne = Mockito.mock(Employee.class);
        Employee mockedBookTwo = Mockito.mock(Employee.class);

        List<Employee> listEmployees = List.of(mockedBookOne, mockedBookTwo);

        Mockito.when(employeeRepository.findAll()).thenReturn(listEmployees);

        // Act
        List<Employee> callRepo = employeeRepository.findAll();
        List<Employee> callService = dataService.getEmployeeDatas_FindAll();

        // Assert
        Assertions.assertThat(callRepo).isEqualTo(callService);
        Assertions.assertThat(callService.size()).isEqualTo(2);
    }

    @Test
    public void DataService_GetEmployeeData_FindById_ReturnsOptionalEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Employee> callService = dataService.getEmployeeData_FindById(1L);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstName_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstName("vorname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstName("vorname");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByLasName_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByLastName("nachname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByLastName("nachname");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByUserName_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByUserName("benutzer")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByUserName("benutzer");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByTitle_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByTitle("job")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByTitle("job");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByPhoneNumber_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByPhoneNumber("+49 157 657457")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByPhoneNumber("+49 157 657457");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByBirthday_ReturnsListEmployee(){
        // Arrange
        Date date = faker.date().birthday();
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByBirthday(date)).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByBirthday(date);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameContaining_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameContaining("eb")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameContaining("eb");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameNotContaining_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameNotContaining("yz")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameNotContaining("yz");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameStartsWith_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameStartsWith("Ar")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameStartsWith("Ar");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameEndsWith_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameEndsWith("na")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameEndsWith("na");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameAndLastName_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameAndLastName("Vorname", "Nachname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameAndLastName("Vorname", "Nachname");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByFirstNameAndLastNameAndUserName_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByFirstNameAndLastNameAndUserName("Vorname", "Nachname", "Username")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByFirstNameAndLastNameAndUserName("Vorname", "Nachname", "Username");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByUserNameAndTitle_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByUserNameAndTitle("Username", "Title")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByUserNameAndTitle("Username", "Title");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByUserNameAndPhoneNumber_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByUserNameAndPhoneNumber("Username", "Phonenumber")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByUserNameAndPhoneNumber("Username", "Phonenumber");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void DataService_GetEmployeeData_FindByTitleAndPhoneNumber_ReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        Mockito.when(employeeRepository.findByTitleAndPhoneNumber("Title", "Phonenumber")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeData_FindByTitleAndPhoneNumber("Title", "Phonenumber");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.size()).isEqualTo(1);
        Assertions.assertThat(callService.get(0)).isEqualTo(mockedBook);
    }
}
