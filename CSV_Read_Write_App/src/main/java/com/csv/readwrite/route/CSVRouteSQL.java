package com.csv.readwrite.route;

import com.csv.readwrite.entity.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVRouteSQL extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Batch insert all data.
        from("file:src/main/resources?fileName=empData.csv&noop=true")
                .unmarshal(new BindyCsvDataFormat(Employee.class))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<Employee> employees = exchange.getIn().getBody(List.class);
                        List<Map<String, Object>> mapList = new ArrayList<>();
                        for(Employee employee : employees) {
                            Map<String, Object> employeeMap = new HashMap<>();
                            employeeMap.put("id", employee.getId());
                            employeeMap.put("name", employee.getName());
                            employeeMap.put("address", employee.getAddress());
                            employeeMap.put("role", employee.getRole());
                            mapList.add(employeeMap);
                        }
                        exchange.getIn().setBody(mapList);
                    }
                })
                .to("log:select?showAll=true&multiline=true")
                .to("sql:INSERT INTO employee(id,name,address,role) VALUES(:#id, :#name, :#address, :#role)?batch=true") // Insert the data into the database
                .log("Data inserted."); // Log the inserted data for debugging

        // Batch insert with batchSize = 10x
//        from("file:src/main/resources?fileName=empData.csv&noop=true")
//                .unmarshal(new BindyCsvDataFormat(Employee.class))
//                .setProperty("CamelBatchSize", constant(10))
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        List<Employee> employees = exchange.getIn().getBody(List.class);
//                        List<Map<String, Object>> mapList = new ArrayList<>();
//                        for(Employee employee : employees) {
//                            Map<String, Object> employeeMap = new HashMap<>();
//                            employeeMap.put("id", employee.getId());
//                            employeeMap.put("name", employee.getName());
//                            employeeMap.put("address", employee.getAddress());
//                            employeeMap.put("role", employee.getRole());
//                            mapList.add(employeeMap);
//                        }
//                        List<List<Map<String, Object>>> listList = new ArrayList<>();
//                        int batchSize = 10;
//                        int count = 0;
//                        List<Map<String, Object>> temp = new ArrayList<>();
//                        for(int i=0; i<mapList.size(); i++){
//                            count++;
//                            temp.add(mapList.get(i));
//                            if(count == batchSize){
//                                listList.add(temp);
//                                count = 0;
//                                temp = new ArrayList<>();
//                            }
//                        }
//                        exchange.getIn().setBody(listList);
//                    }
//                })
//                .split(body())
//                .log("${body}")
//                .to("sql:INSERT INTO employee(id,name,address,role) VALUES(:#id, :#name, :#address, :#role)?batch=true") // Insert the data into the database
//                .log("Data inserted."); // Log the inserted data for debugging
    }
}
