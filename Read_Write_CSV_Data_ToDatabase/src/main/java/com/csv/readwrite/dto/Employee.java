package com.csv.readwrite.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
@CsvRecord(separator = ",", skipField = true, skipFirstLine = true)
public class Employee {

	@DataField(pos = 1)
	@Id
	private Integer id;

	@DataField(pos = 2)
	private String name;

	@DataField(pos = 3)
	private String address;

	@DataField(pos = 4)
	private String role;

}
