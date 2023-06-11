package de.drue.EmployeeApp.Service;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private DataService dataService;

    @Test
    public void dataServiceGetEmployeeDataFindAllReturnsListEmployee(){
        // Arrange
        Employee mockedBookOne = Mockito.mock(Employee.class);
        Employee mockedBookTwo = Mockito.mock(Employee.class);

        List<Employee> listEmployees = List.of(mockedBookOne, mockedBookTwo);

        when(employeeRepository.findAll()).thenReturn(listEmployees);

        // Act
        List<Employee> callRepo = employeeRepository.findAll();
        List<Employee> callService = dataService.getEmployeeDataFindAll();

        // Assert
        assertThat(callRepo).isEqualTo(callService);
        assertThat(callService.size()).isEqualTo(2);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByIdReturnsOptionalEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Employee> callService = dataService.getEmployeeDataFindById(1L);

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstName("vorname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstName("vorname");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByLasNameReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByLastName("nachname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByLastName("nachname");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByUserNameReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByUserName("benutzer")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByUserName("benutzer");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameContainingReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameContaining("eb")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameContaining("eb");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameNotContainingReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameNotContaining("yz")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameNotContaining("yz");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameStartsWithReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameStartsWith("Ar")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameStartsWith("Ar");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameEndsWithReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameEndsWith("na")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameEndsWith("na");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameAndLastNameReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameAndLastName("Vorname", "Nachname")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameAndLastName("Vorname", "Nachname");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByFirstNameAndLastNameAndUserNameReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByFirstNameAndLastNameAndUserName("Vorname", "Nachname", "Username")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByFirstNameAndLastNameAndUserName("Vorname", "Nachname", "Username");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByAddressCityReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByAddressCity("Stuttgart")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByAddressCity("Stuttgart");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }

    @Test
    public void dataServiceGetEmployeeDataFindByCourseTitleReturnsListEmployee(){
        // Arrange
        Employee mockedBook = Mockito.mock(Employee.class);

        when(employeeRepository.findByCourseTitle("Spring Boot")).thenReturn(List.of(mockedBook));

        // Act
        List<Employee> callService = dataService.getEmployeeDataFindByCourseTitle("Spring Boot");

        // Assert
        assertThat(callService).isNotNull();
        assertThat(callService.size()).isEqualTo(1);
        assertThat(callService.get(0)).isEqualTo(mockedBook);
    }
}
