package com.csv.readwrite.entity;

import lombok.*;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
@CsvRecord(separator = ",", skipField = true, skipFirstLine = true)
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DataField(pos = 1)
    private Integer id;

    @DataField(pos = 2)
    private String name;

    @DataField(pos = 3)
    private String address;

    @DataField(pos = 4)
    private String role;

}
