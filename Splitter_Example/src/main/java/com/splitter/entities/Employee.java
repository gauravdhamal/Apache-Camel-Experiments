package com.splitter.entities;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipField = true, skipFirstLine = true)
public class Employee {
    @DataField(pos = 1)
    private Integer id;
    @DataField(pos = 2)
    private String name;
    @DataField(pos = 3)
    private String address;
    @DataField(pos = 4)
    private String role;

}
