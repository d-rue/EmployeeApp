# EmployeeApp
A simple Spring Boot employee application, which saves randomly generated employee data to an In-Memory H2 Database at start up.  
This is handled by a Employee Repository Interface of JpaRepository covering the Book Entity.  
OneToOne relationshit to Workstation Entity.  
OneToMany relationship to Address Entity.

Accessible Endpoints at http://localhost:8080:

| HTTP Methode | URL | Decription |
| --- | --- | --- |
| GET: | "/get/all" | List all Data
| GET: | "/search/id/{id}" | Get one Employee by Id
| GET: | "/search/firstname/{firstname}" | List Employee by Firstname
| GET: | "/search/lastname/{lastname}" | List Employee by Lastname
| GET: | "/search/firstnameandlastname/{firstname}/{lastname}" | List Employee by Firstname and Lastname
| GET: | "/search/firstnamecontaining/{string}" | List Employee containing string in Firstname

Tests are in the Test Folder:

| File name |
| --- |
| DataControllerTests |
| DataServiceTests |
| EmployeeRepositoryTests |

CI/CD:  
Java with Maven  
Build & Push Docker image
