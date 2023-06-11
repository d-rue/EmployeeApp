package de.drue.EmployeeApp.Controller;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DataController {
    private final DataService dataService;

    @RequestMapping(value = "/get/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listData(){
        return new ResponseEntity<>(dataService.getEmployeeDataFindAll(),
                                    HttpStatus.OK);
    }

    @RequestMapping(value = "/search/id/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Employee>> listDataById(@PathVariable final long id){
        return new ResponseEntity<>(dataService.getEmployeeDataFindById(id),
                HttpStatus.OK);
    }
    @RequestMapping(value = "/search/fn/{firstname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstName(@PathVariable final String firstname){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstName(firstname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/ln/{lastname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByLastName(@PathVariable final String lastname){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByLastName(lastname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/un/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByUserName(@PathVariable final String username){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByUserName(username),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fncontain/{string}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameContaining(@PathVariable final String string){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameContaining(string),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fnnotcontain/{string}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameNotContaining(@PathVariable final String string){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameNotContaining(string),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fnstarts/{string}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameStartsWith(@PathVariable final String string){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameStartsWith(string),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fnends/{string}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameEndsWith(@PathVariable final String string){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameEndsWith(string),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fnaln/{firstname}/{lastname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameAndLastName(@PathVariable final String firstname, @PathVariable final String lastname){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameAndLastName(firstname, lastname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/fnalnaun/{firstname}/{lastname}/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameAndLastNameAndUserName(@PathVariable final String firstname, @PathVariable final String lastname, @PathVariable final String username){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByFirstNameAndLastNameAndUserName(firstname, lastname, username),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/address/{city}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByAddressCity(@PathVariable final String city){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByAddressCity(city),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/course/{title}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByCourseTitle(@PathVariable final String title){
        return new ResponseEntity<>(dataService.getEmployeeDataFindByCourseTitle(title),
                HttpStatus.OK);
    }
}
