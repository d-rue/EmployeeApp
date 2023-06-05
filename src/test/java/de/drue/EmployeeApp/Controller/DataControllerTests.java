package de.drue.EmployeeApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import de.drue.EmployeeApp.Service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = DataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class DataControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataService dataService;
    Faker faker = new Faker(Locale.GERMAN);
    private Employee firstEmployee;
    private Employee secondEmployee;
    private List<Employee> listEmployees;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        firstEmployee = Employee.builder()
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

        secondEmployee = Employee.builder()
                .id(2)
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
    public void DataController_ListData_ReturnsListEmployees() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeDatas_FindAll()).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/get/all"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(listEmployees.get(0).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(listEmployees.get(0).getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").value(listEmployees.get(0).getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(listEmployees.get(0).getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").value(listEmployees.get(0).getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").value(listEmployees.get(0).getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").value(listEmployees.get(0).getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").value(listEmployees.get(0).getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").value(listEmployees.get(0).getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").value(listEmployees.get(0).getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").value(listEmployees.get(0).getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").value(listEmployees.get(0).getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").value(listEmployees.get(0).getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").value(listEmployees.get(0).getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").value(listEmployees.get(0).getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").value(listEmployees.get(0).getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").value(listEmployees.get(0).getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").value(listEmployees.get(0).getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").value(listEmployees.get(0).getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").value(listEmployees.get(0).getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").value(listEmployees.get(0).getWorkstation().getName()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].firstName").value(listEmployees.get(1).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].lastName").value(listEmployees.get(1).getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].userName").value(listEmployees.get(1).getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].title").value(listEmployees.get(1).getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].phoneNumber").value(listEmployees.get(1).getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].id").value(listEmployees.get(1).getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].country").value(listEmployees.get(1).getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].state").value(listEmployees.get(1).getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].zip").value(listEmployees.get(1).getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].city").value(listEmployees.get(1).getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].streetName").value(listEmployees.get(1).getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[0].streetAddressNumber").value(listEmployees.get(1).getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].id").value(listEmployees.get(1).getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].country").value(listEmployees.get(1).getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].state").value(listEmployees.get(1).getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].zip").value(listEmployees.get(1).getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].city").value(listEmployees.get(1).getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].streetName").value(listEmployees.get(1).getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].addresses.[1].streetAddressNumber").value(listEmployees.get(1).getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].workstation.id").value(listEmployees.get(1).getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].workstation.name").value(listEmployees.get(1).getWorkstation().getName()));
    }

    @Test
    public void DataController_ListDataById_ReturnsOptionalEmployee() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeData_FindById(1L)).thenReturn(Optional.of(firstEmployee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/id/1"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(firstEmployee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(firstEmployee.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value(firstEmployee.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(firstEmployee.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(firstEmployee.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].id").value(firstEmployee.getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].country").value(firstEmployee.getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].state").value(firstEmployee.getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].zip").value(firstEmployee.getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].city").value(firstEmployee.getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].streetName").value(firstEmployee.getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[0].streetAddressNumber").value(firstEmployee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].id").value(firstEmployee.getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].country").value(firstEmployee.getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].state").value(firstEmployee.getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].zip").value(firstEmployee.getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].city").value(firstEmployee.getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].streetName").value(firstEmployee.getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresses.[1].streetAddressNumber").value(firstEmployee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.workstation.id").value(firstEmployee.getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.workstation.name").value(firstEmployee.getWorkstation().getName()));
    }

    @Test
    public void DataController_ListDataByFirstName_ReturnsListEmployee() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeData_FindByFirstName("vorname")).thenReturn(List.of(firstEmployee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/firstname/vorname"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(firstEmployee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(firstEmployee.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").value(firstEmployee.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(firstEmployee.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").value(firstEmployee.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").value(firstEmployee.getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").value(firstEmployee.getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").value(firstEmployee.getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").value(firstEmployee.getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").value(firstEmployee.getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").value(firstEmployee.getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").value(firstEmployee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").value(firstEmployee.getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").value(firstEmployee.getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").value(firstEmployee.getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").value(firstEmployee.getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").value(firstEmployee.getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").value(firstEmployee.getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").value(firstEmployee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").value(firstEmployee.getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").value(firstEmployee.getWorkstation().getName()));
    }

    @Test
    public void DataController_ListDataByLastName_ReturnsListEmployee() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeData_FindByLastName("nachname")).thenReturn(List.of(firstEmployee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/lastname/nachname"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(firstEmployee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(firstEmployee.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").value(firstEmployee.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(firstEmployee.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").value(firstEmployee.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").value(firstEmployee.getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").value(firstEmployee.getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").value(firstEmployee.getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").value(firstEmployee.getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").value(firstEmployee.getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").value(firstEmployee.getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").value(firstEmployee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").value(firstEmployee.getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").value(firstEmployee.getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").value(firstEmployee.getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").value(firstEmployee.getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").value(firstEmployee.getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").value(firstEmployee.getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").value(firstEmployee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").value(firstEmployee.getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").value(firstEmployee.getWorkstation().getName()));
    }

    @Test
    public void DataController_ListDataByFirstNameAndLastName_ReturnsListEmployee() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeData_FindByFirstNameAndLastName("vorname", "nachname")).thenReturn(List.of(firstEmployee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/firstnameandlastname/vorname/nachname"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(firstEmployee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(firstEmployee.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").value(firstEmployee.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(firstEmployee.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").value(firstEmployee.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").value(firstEmployee.getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").value(firstEmployee.getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").value(firstEmployee.getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").value(firstEmployee.getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").value(firstEmployee.getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").value(firstEmployee.getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").value(firstEmployee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").value(firstEmployee.getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").value(firstEmployee.getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").value(firstEmployee.getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").value(firstEmployee.getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").value(firstEmployee.getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").value(firstEmployee.getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").value(firstEmployee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").value(firstEmployee.getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").value(firstEmployee.getWorkstation().getName()));
    }

    @Test
    public void DataController_ListDataByFirstNameContaining_ReturnsListEmployee() throws Exception {
        // Arrange
        Mockito.when(dataService.getEmployeeData_FindByFirstNameContaining("ab")).thenReturn(List.of(firstEmployee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/firstnamecontaining/ab"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value(firstEmployee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(firstEmployee.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName").value(firstEmployee.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(firstEmployee.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber").value(firstEmployee.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].id").value(firstEmployee.getAddresses().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].country").value(firstEmployee.getAddresses().get(0).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].state").value(firstEmployee.getAddresses().get(0).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].zip").value(firstEmployee.getAddresses().get(0).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].city").value(firstEmployee.getAddresses().get(0).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetName").value(firstEmployee.getAddresses().get(0).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[0].streetAddressNumber").value(firstEmployee.getAddresses().get(0).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].id").value(firstEmployee.getAddresses().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].country").value(firstEmployee.getAddresses().get(1).getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].state").value(firstEmployee.getAddresses().get(1).getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].zip").value(firstEmployee.getAddresses().get(1).getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].city").value(firstEmployee.getAddresses().get(1).getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetName").value(firstEmployee.getAddresses().get(1).getStreetName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].addresses.[1].streetAddressNumber").value(firstEmployee.getAddresses().get(1).getStreetAddressNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.id").value(firstEmployee.getWorkstation().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].workstation.name").value(firstEmployee.getWorkstation().getName()));
    }
}
