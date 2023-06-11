package de.drue.EmployeeApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import de.drue.EmployeeApp.Service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = DataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class DataControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataService dataService;
    @Autowired
    private ObjectMapper objectMapper;
    private Faker faker = new Faker(Locale.GERMAN);
    private Employee employee;
    private List<Employee> listEmployees;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .id(1)
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

        listEmployees = List.of(employee);
    }

    @Test
    public void dataControllerListDataReturnsListEmployees() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindAll()).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/get/all"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(listEmployees.get(0).getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(listEmployees.get(0).getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(listEmployees.get(0).getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(listEmployees.get(0).getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(listEmployees.get(0).getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(listEmployees.get(0).getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(listEmployees.get(0).getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(listEmployees.get(0).getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(listEmployees.get(0).getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(listEmployees.get(0).getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(listEmployees.get(0).getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(listEmployees.get(0).getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(listEmployees.get(0).getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(listEmployees.get(0).getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(listEmployees.get(0).getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(listEmployees.get(0).getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(listEmployees.get(0).getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(listEmployees.get(0).getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(listEmployees.get(0).getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(listEmployees.get(0).getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(listEmployees.get(0).getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByIdReturnsOptionalEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindById(1L)).thenReturn(Optional.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/id/1"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(employee))
                );
    }

    @Test
    public void dataControllerListDataByFirstNameReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstName("vorname")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fn/vorname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByLastNameReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByLastName("nachname")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/ln/nachname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByUserNameReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByUserName("username")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/un/username"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameContainingReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameContaining("ab")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fncontain/ab"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameNotContainingReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameNotContaining("z_y_q")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnnotcontain/z_y_q"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameStartsWithReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameStartsWith("am")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnstarts/am"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameEndsWithReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameEndsWith("fe")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnends/fe"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameAndLastNameReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameAndLastName("vorname", "nachname")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnaln/vorname/nachname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByFirstNameAndLastNameAndUserNameReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByFirstNameAndLastNameAndUserName("vorname", "nachname", "username")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnalnaun/vorname/nachname/username"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByAddressCityReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByAddressCity("Stuttgart")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/address/Stuttgart"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }

    @Test
    public void dataControllerListDataByCourseTitleReturnsListEmployee() throws Exception {
        // Arrange
        when(dataService.getEmployeeDataFindByCourseTitle("Spring Boot")).thenReturn(List.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/course/Spring Boot"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").isString())
                .andExpect(jsonPath("$.[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").isString())
                .andExpect(jsonPath("$.[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("$.[0].userName").isString())
                .andExpect(jsonPath("$.[0].userName").value(employee.getUserName()))
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title").value(employee.getTitle()))
                .andExpect(jsonPath("$.[0].birthday").exists())
                .andExpect(jsonPath("$.[0].phoneNumber").isString())
                .andExpect(jsonPath("$.[0].phoneNumber").value(employee.getPhoneNumber()))
                .andExpect(jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].id").value(employee.getAddresses().get(0).getId()))
                .andExpect(jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].country").value(employee.getAddresses().get(0).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].state").value(employee.getAddresses().get(0).getState()))
                .andExpect(jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].zip").value(employee.getAddresses().get(0).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].city").value(employee.getAddresses().get(0).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[0].streetName").value(employee.getAddresses().get(0).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[0].streetAddressNumber").value(employee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].id").value(employee.getAddresses().get(1).getId()))
                .andExpect(jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].country").value(employee.getAddresses().get(1).getCountry()))
                .andExpect(jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].state").value(employee.getAddresses().get(1).getState()))
                .andExpect(jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].zip").value(employee.getAddresses().get(1).getZip()))
                .andExpect(jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].city").value(employee.getAddresses().get(1).getCity()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(jsonPath("$.[0].addresses.[1].streetName").value(employee.getAddresses().get(1).getStreetName()))
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(jsonPath("$.[0].addresses.[1].streetAddressNumber").value(employee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(jsonPath("$.[0].workstation.id").value(employee.getWorkstation().getId()))
                .andExpect(jsonPath("$.[0].workstation.name").isString())
                .andExpect(jsonPath("$.[0].workstation.name").value(employee.getWorkstation().getName()));
    }
}
