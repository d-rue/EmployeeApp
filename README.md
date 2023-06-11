# EmployeeApp
Spring Boot Application, which saves randomly generated employee data to an In-Memory H2 Database at start up.  
  
OneToOne relationship to Workstation Entity.  
OneToMany relationship to Address Entity.  
ManyToMany relationship to Course Entity.  
  
Some JPA Queries in the EmployeeRepository
  
  
Accessible Endpoints at localhost:8080  
  
| HTTP Methode | URL                                               | Decription                                        |
| --- |---------------------------------------------------|---------------------------------------------------|
| GET: | "/get/all"                                        | List all Data                                     |
| GET: | "/search/id/{id}"                                 | Get one Employee by ID                            |
| GET: | "/search/fn/{firstname}"                          | List Employee by Firstname                        |
| GET: | "/search/ln/{lastname}"                           | List Employee by Lastname                         |
| GET: | "/search/un/{username}"                           | List Employee by Username                         |
| GET: | "/search/fncontain/{string}"                      | List Employee containing string in Firstname      |
| GET: | "/search/fnnotcontain/{string}"                   | List Employee not containing string in Firstname  |
| GET: | "/search/fnstarts/{string}"                       | List Employee starting with string in Firstname   |
| GET: | "/search/fnends/{string}"                         | List Employee ending with string in Firstname     |
| GET: | "/search/fnaln/{firstname}/{lastname}"            | List Employee by Firstname and Lastname           |
| GET: | "/search/fnalnaun/{firstname}/{lastname}/{username}" | List Employee by Firstname, Lastname and Username |
| GET: | "/search/address/{city}"                         | List Employee by City of Addresses                |
| GET: | "/search/course/{title}"                         | List Employee by Title of Courses                 |
  
  
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
