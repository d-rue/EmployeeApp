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
    private final Faker faker = new Faker(Locale.GERMAN);
    private Employee employee;
    private List<Employee> listEmployees;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .id(1)
                .firstName(faker.address().firstName())
                .lastName(faker.name().lastName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
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
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindAll()).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/get/all"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByIdReturnsOptionalEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(employee);
        when(dataService.getEmployeeDataFindById(1L)).thenReturn(Optional.of(employee));

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/id/1"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstName("vorname")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fn/vorname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByLastNameReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByLastName("nachname")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/ln/nachname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByUserNameReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByUserName("username")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/un/username"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameContainingReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameContaining("ab")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fncontain/ab"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameNotContainingReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameNotContaining("z_y_q")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnnotcontain/z_y_q"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameStartsWithReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameStartsWith("am")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnstarts/am"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameEndsWithReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameEndsWith("fe")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnends/fe"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameAndLastNameReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameAndLastName("vorname", "nachname")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnaln/vorname/nachname"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByFirstNameAndLastNameAndUserNameReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByFirstNameAndLastNameAndUserName("vorname", "nachname", "username")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/fnalnaun/vorname/nachname/username"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByAddressCityReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByAddressCity("Stuttgart")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/address/Stuttgart"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void dataControllerListDataByCourseTitleReturnsListEmployee() throws Exception {
        // Arrange
        String expected = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(listEmployees);
        when(dataService.getEmployeeDataFindByCourseTitle("Spring Boot")).thenReturn(listEmployees);

        // Act
        ResultActions response = mockMvc.perform(get("/api/search/course/Spring Boot"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
