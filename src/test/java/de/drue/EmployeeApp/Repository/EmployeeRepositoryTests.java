package de.drue.EmployeeApp.Repository;

import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.github.javafaker.Faker;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTests {
    @Autowired
    private  EmployeeRepository employeeRepository;
    private Faker faker = new Faker(Locale.GERMAN);
    private Employee firstEmployee;
    private Employee secondEmployee;
    private List<Employee> listEmployees;

    @BeforeEach
    public void init() throws ParseException {
        firstEmployee = Employee.builder()
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

        secondEmployee = Employee.builder()
                .firstName(faker.address().firstName())
                .lastName(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .birthday(faker.date().birthday())
                .title(faker.name().title())
                .userName(faker.artist().name())
                .workstation(Workstation.builder()
                        .name("Laptop")
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

        listEmployees = List.of(firstEmployee, secondEmployee);
    }

    @Test
    public void EmployeeRepository_Save_ReturnsSavedEmployee(){
        // Arrange

        // Act
        Employee savedEmployee = employeeRepository.save(firstEmployee);

        // Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void EmployeeRepository_FindAll_ReturnsMoreThanOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);
        employeeRepository.save(secondEmployee);

        List<Employee> employeeList = employeeRepository.findAll();

        //Assert
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void EmployeeRepository_FindById_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        Employee savedEmployee = employeeRepository.findById(firstEmployee.getId()).get();

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstName_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstName(firstEmployee.getFirstName());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByLastName_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByLastName(firstEmployee.getLastName());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByUserName_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByUserName(firstEmployee.getUserName());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByTitle_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByTitle(firstEmployee.getTitle());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByPhoneNumber_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByPhoneNumber(firstEmployee.getPhoneNumber());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByBirthday_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByBirthday(firstEmployee.getBirthday());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstNameContaining_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameContaining(firstEmployee.getFirstName().substring(firstEmployee.getFirstName().length()/2 - 1, firstEmployee.getFirstName().length()/2 + 2));

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstNameStartsWith_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameStartsWith(firstEmployee.getFirstName().substring(0, 2));

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstNameEndsWith_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameEndsWith(firstEmployee.getFirstName().substring(firstEmployee.getFirstName().length() - 2));

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstNameAndLastName_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameAndLastName(firstEmployee.getFirstName(), firstEmployee.getLastName());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByFirstNameAndLastNameAndUserName_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByFirstNameAndLastNameAndUserName(firstEmployee.getFirstName(), firstEmployee.getLastName(), firstEmployee.getUserName());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByUserNameAndTitle_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByUserNameAndTitle(firstEmployee.getUserName(), firstEmployee.getTitle());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByUserNameAndPhoneNumber_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByUserNameAndPhoneNumber(firstEmployee.getUserName(), firstEmployee.getPhoneNumber());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }

    @Test
    public void EmployeeRepository_FindByTitleAndPhoneNumber_ReturnsOneEmployee(){
        // Arrange

        // Act
        employeeRepository.save(firstEmployee);

        List<Employee> savedEmployee = employeeRepository.findByTitleAndPhoneNumber(firstEmployee.getTitle(), firstEmployee.getPhoneNumber());

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.size()).isEqualTo(1);
        Assertions.assertThat(savedEmployee.get(0)).isEqualTo(firstEmployee);
    }
}
