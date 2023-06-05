package de.drue.EmployeeApp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_EMPLOYEE_ID", referencedColumnName = "ID")
    private Set<Address> addresses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_WORKSTATION_ID", referencedColumnName = "ID")
    @JsonManagedReference
    private Workstation workstation;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "EMPLOYEE_COURSE",
            joinColumns = {
                    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
            })
    private Set<Course> courses;
}
