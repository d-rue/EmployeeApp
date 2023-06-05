package de.drue.EmployeeApp.Bootstrap;

import de.drue.EmployeeApp.Entity.Address;
import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Entity.Workstation;
import de.drue.EmployeeApp.Repository.EmployeeRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.javafaker.Faker;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Slf4j
@NoArgsConstructor
public class DatabaseBootstrap implements InitializingBean {
	@Autowired
    private EmployeeRepository employeeRepository;
    private Random randomInt = new Random();
    @Override
    public void afterPropertiesSet() throws Exception {
        Faker faker = new Faker(new Locale("de"));

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
                    .addresses(new ArrayList<>())
                    .build();

            // Add Addresses to Employee - OneToMany
            List<Address> listAddresses = new ArrayList<>();
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
                listAddresses.add(tmpAddress);
            }
            tmpEmp.setAddresses(listAddresses);

            // Add Workstation to Employee - OneToOne
            Workstation tmpWorkstation = Workstation.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            tmpEmp.setWorkstation(tmpWorkstation);

            setEmployees.add(tmpEmp);
        }
        employeeRepository.saveAll(setEmployees);

        log.info("Bootstrap finished");
    }
}
