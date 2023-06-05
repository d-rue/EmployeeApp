package de.drue.EmployeeApp.Controller;

import de.drue.EmployeeApp.Entity.Employee;
import de.drue.EmployeeApp.Service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DataController {
    private final DataService dataService;
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(value = "/get/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listData(){
        return new ResponseEntity<>(dataService.getEmployeeDatas_FindAll(),
                                    HttpStatus.OK);
    }

    @RequestMapping(value = "/search/id/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Employee>> listDataById(@PathVariable long id){
        return new ResponseEntity<>(dataService.getEmployeeData_FindById(id),
                HttpStatus.OK);
    }
    @RequestMapping(value = "/search/firstname/{firstname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstName(@PathVariable String firstname){
        return new ResponseEntity<>(dataService.getEmployeeData_FindByFirstName(firstname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/lastname/{lastname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByLastName(@PathVariable String lastname){
        return new ResponseEntity<>(dataService.getEmployeeData_FindByLastName(lastname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/firstnameandlastname/{firstname}/{lastname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameAndLastName(@PathVariable String firstname, @PathVariable String lastname){
        return new ResponseEntity<>(dataService.getEmployeeData_FindByFirstNameAndLastName(firstname, lastname),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/firstnamecontaining/{string}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listDataByFirstNameContaining(@PathVariable String string){
        return new ResponseEntity<>(dataService.getEmployeeData_FindByFirstNameContaining(string),
                HttpStatus.OK);
    }
}
