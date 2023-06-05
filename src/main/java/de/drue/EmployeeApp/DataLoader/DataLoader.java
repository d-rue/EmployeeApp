package de.drue.EmployeeApp.DataLoader;

import com.github.javafaker.Faker;
import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Course;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import de.drue.EmployeeApp.Repository.CourseRepository;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {
    private Random randomInt = new Random();
    private Faker faker = new Faker(new Locale("de"));
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;

    public DataLoader(CourseRepository courseRepository, EmployeeRepository employeeRepository) {
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
    }

    public void run(ApplicationArguments args) {
        // Courses
        Set<Course> setCourses = new HashSet<>();

        Course courseJava = Course.builder()
                .title("Java Programming")
                .build();
        setCourses.add(courseJava);
        Course courseQuery = Course.builder()
                .title("Query Language")
                .build();
        setCourses.add(courseQuery);
        Course courseSpring = Course.builder()
                .title("Spring Boot")
                .build();
        setCourses.add(courseSpring);

        courseRepository.saveAll(setCourses);

        // Employees
        Set<Employee> setEmployees = new HashSet<>();

        for (int i = 0; i <= 50; i++){
            Employee tmpEmp = Employee.builder()
                    .firstName(faker.address().firstName())
                    .lastName(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .birthday(faker.date().birthday())
                    .title(faker.name().title())
                    .userName(faker.artist().name())
                    .addresses(new HashSet<>())
                    .courses(new HashSet<>())
                    .build();

            // Add Addresses to Employee - OneToMany
            Set<Address> setAddresses = new HashSet<>();
            for (int n = 0; n <=randomInt.nextInt(5); n++){
                Address tmpAddress = Address
                        .builder()
                        .country(faker.address().countryCode())
                        .state(faker.address().state())
                        .zip(Integer.parseInt(faker.address().zipCode()))
                        .city(faker.address().cityName())
                        .streetName(faker.address().streetName())
                        .streetAddressNumber(Integer.parseInt(faker.address().streetAddressNumber()))
                        .build();
                setAddresses.add(tmpAddress);
            }
            tmpEmp.setAddresses(setAddresses);

            // Add Workstation to Employee - OneToOne
            Workstation tmpWorkstation = Workstation.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            tmpEmp.setWorkstation(tmpWorkstation);

            Integer tmpInt = randomInt.nextInt(3);
            if (tmpInt.equals(1)){
                tmpEmp.getCourses().add(courseSpring);
            }
            else if (tmpInt.equals(2)) {
                tmpEmp.getCourses().add(courseSpring);
                tmpEmp.getCourses().add(courseQuery);
            }
            else if (tmpInt.equals(3)) {
                tmpEmp.getCourses().add(courseSpring);
                tmpEmp.getCourses().add(courseJava);
            }
            else {
                tmpEmp.getCourses().add(courseJava);
                tmpEmp.getCourses().add(courseSpring);
                tmpEmp.getCourses().add(courseQuery);
            }

            setEmployees.add(tmpEmp);
        }
        employeeRepository.saveAll(setEmployees);

        log.info("Data saved to db");
    }
}
