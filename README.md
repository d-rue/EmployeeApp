# EmployeeApp
Spring Boot Application, which saves randomly generated employee data to an In-Memory H2 Database at start up.  
  
OneToOne relationship to Workstation Entity.  
OneToMany relationship to Address Entity.  
ManyToMany relationship to Course Entity.  
  
Some JPA Queries in the EmployeeRepository
  
  
Accessible Endpoints  
dev-Environment: localhost:8080  
prod-Environment: localhost:80  
  
| HTTP Methode | URL                                               | Decription                                        |
| --- |---------------------------------------------------|---------------------------------------------------|
| GET: | "/api/get/all"                                        | List all Data                                     |
| GET: | "/api/search/id/{id}"                                 | Get one Employee by ID                            |
| GET: | "/api/search/fn/{firstname}"                          | List Employee by Firstname                        |
| GET: | "/api/search/ln/{lastname}"                           | List Employee by Lastname                         |
| GET: | "/api/search/un/{username}"                           | List Employee by Username                         |
| GET: | "/api/search/fncontain/{string}"                      | List Employee containing string in Firstname      |
| GET: | "/api/search/fnnotcontain/{string}"                   | List Employee not containing string in Firstname  |
| GET: | "/api/search/fnstarts/{string}"                       | List Employee starting with string in Firstname   |
| GET: | "/api/search/fnends/{string}"                         | List Employee ending with string in Firstname     |
| GET: | "/api/search/fnaln/{firstname}/{lastname}"            | List Employee by Firstname and Lastname           |
| GET: | "/api/search/fnalnaun/{firstname}/{lastname}/{username}" | List Employee by Firstname, Lastname and Username |
| GET: | "/api/search/address/{city}"                         | List Employee by City of Addresses                |
| GET: | "/api/search/course/{title}"                         | List Employee by Title of Courses                 |
  
OpenAPI/Swagger in dev-Environment at localhost:8080/swagger-ui.html  
  
  
Tests are in the Test Folder:  
  
| Filename |
| --- |
| DataControllerTests |
| DataServiceTests |
| EmployeeRepositoryTests |
  
  
CI/CD:  
Java with Maven  
Build & Push Docker image  
  
Super Linter
