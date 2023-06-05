package de.drue.EmployeeApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "COUNTRY_CODE")
    private String country;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIP_CODE")
    private int zip;
    @Column(name = "CITY_NAME")
    private String city;
    @Column(name = "STREET_ADDRESS_NAME")
    private String streetName;
    @Column(name = "STREET_ADDRESS_NUMBER")
    private int streetAddressNumber;
}
