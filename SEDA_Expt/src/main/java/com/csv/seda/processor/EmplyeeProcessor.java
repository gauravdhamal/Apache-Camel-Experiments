package com.csv.seda.processor;

import com.csv.seda.entity.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmplyeeProcessor implements Processor {

    @Override
    public void process(Exchange exchange){
        List<Employee> employeeList = exchange.getIn().getBody(List.class);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(Employee employee : employeeList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", employee.getId());
            map.put("name", employee.getName());
            map.put("address", employee.getAddress());
            map.put("role", employee.getRole());
            mapList.add(map);
        }
        exchange.getIn().setBody(mapList);
        System.out.println("Body set in exchange.");
    }

}
