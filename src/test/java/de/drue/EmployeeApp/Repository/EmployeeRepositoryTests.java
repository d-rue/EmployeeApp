package de.drue.EmployeeApp.Repository;

import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    private final Faker faker = new Faker(Locale.GERMAN);
    private Employee employee;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .firstName(faker.address().firstName())
                .lastName(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .birthday(faker.date().birthday())
                .title(faker.name().title())
                .userName(faker.artist().name())
                .workstation(Workstation.builder()
                        .name("Computer")
                        .build())
                .addresses(List.of(
                        Address.builder()
                                .country(faker.address().countryCode())
                                .state(faker.address().state())
                                .zip(Integer.parseInt(faker.address().zipCode()))
                                .city(faker.address().cityName())
                                .streetName(faker.address().streetName())
                                .streetAddressNumber(Integer.parseInt(faker.address().streetAddressNumber()))
                                .build(),
                        Address.builder()
                                .country(faker.address().countryCode())
                                .state(faker.address().state())
                                .zip(Integer.parseInt(faker.address().zipCode()))
                                .city(faker.address().cityName())
                                .streetName(faker.address().streetName())
                                .streetAddressNumber(Integer.parseInt(faker.address().streetAddressNumber()))
                                .build()))
                .build();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void employeeRepositorySaveReturnsEmployee(){
        // Arrange

        // Act
        Employee savedEmployee = employeeRepository.save(employee);

        // Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void employeeRepositoryFindAllReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> employeeList = employeeRepository.findAll();

        //Assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(1);
    }

    @Test
    public void employeeRepositoryFindByIdReturnsEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstName(employee.getFirstName());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByLastNameReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByLastName(employee.getLastName());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByUserNameReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByUserName(employee.getUserName());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameContainingReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameContaining(employee.getFirstName().substring(employee.getFirstName().length()/2 - 1, employee.getFirstName().length()/2 + 2));

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameNotContainingReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameNotContaining("z_y_q");

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameStartsWithReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameStartsWith(employee.getFirstName().substring(0, 2));

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameEndsWithReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameEndsWith(employee.getFirstName().substring(employee.getFirstName().length() - 2));

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameAndLastNameReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByFirstNameAndLastNameAndUserNameReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameAndLastNameAndUserName(employee.getFirstName(), employee.getLastName(), employee.getUserName());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }

    @Test
    public void employeeRepositoryFindByAddressCityReturnsListEmployee(){
        // Arrange

        // Act
        employeeRepository.save(employee);

        List<Employee> savedEmployee = employeeRepository.findByAddressCity(employee.getAddresses().get(0).getCity());

        //Assert
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.size()).isEqualTo(1);
        assertThat(savedEmployee.get(0)).isEqualTo(employee);
    }
}
