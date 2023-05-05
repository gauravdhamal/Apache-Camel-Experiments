package com.csv.readwrite.route;

import com.csv.readwrite.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
@Setter
@Getter
public class CSVRouteJDBC extends RouteBuilder {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure() throws Exception{
        // Insert root single-single query.
        from("file:src/main/resources?fileName=empData.csv&noop=true")
                .unmarshal(new BindyCsvDataFormat(Employee.class))
                .split(body())
                .streaming()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Employee employee = exchange.getIn().getBody(Employee.class);
                        String query = "INSERT INTO employee(id,name,address,role) VALUES("+employee.getId()+",'"+employee.getName()+"','"+employee.getAddress()+"','"+employee.getRole()+"')";
                        exchange.getIn().setBody(query);
                    }
                })
//                .to("log:select?showAll=true&multiline=true")
                .to("jdbc:dataSource")
                .log("Data inserted to database success.");

//        // Insert root with batchSize = 2 inserting data in batch.
//        from("file:src/main/resources?fileName=empData.csv&noop=true")
//                .unmarshal(new BindyCsvDataFormat(Employee.class))
//                .setProperty("CamelBatchSize", constant(2))
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        List<Employee> employees = exchange.getIn().getBody(List.class);
//                        StringBuilder finalQuery = new StringBuilder();
//                        StringBuilder multiQuery = new StringBuilder("INSERT INTO employee(id,name,address,role) VALUES");
//                        int count = 0;
//                        int batchSize = 2;
//                        for(Employee employee : employees){
//                            count++;
//                            String query = "("+employee.getId()+",'"+employee.getName()+"','"+employee.getAddress()+"','"+employee.getRole()+"')";
//                            multiQuery.append(query + ",");
//                            if(count == batchSize){
//                                multiQuery.deleteCharAt(multiQuery.length()-1);
//                                finalQuery.append(multiQuery+";");
//                                count = 0;
//                                multiQuery = new StringBuilder("INSERT INTO employee(id,name,address,role) VALUES");
//                            }
//                        }
//                        finalQuery.deleteCharAt(finalQuery.length()-1);
//                        System.out.println("finalQuery : "+finalQuery);
//                        exchange.getIn().setBody(finalQuery);
//                    }
//                })
//                .split(body().tokenize(";"))
//                .log("Body : ${body}")
////                .to("log:select?showAll=true&multiline=true")
//                .to("jdbc:dataSource")
//                .log("Data inserted to database success.");

//        // Select root
//        from("direct:select").setBody(constant("SELECT * FROM employee")).to("jdbc:dataSource")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) exchange.getIn().getBody();
//                        List<Employee> employeeList = new ArrayList<>();
//                        for(Map<String, String> data : dataList){
//                            Employee employee = new Employee();
//                            employee.setId(Integer.parseInt(data.get("id")));
//                            employee.setName(data.get("name"));
//                            employee.setAddress(data.get("address"));
//                            employee.setRole(data.get("role"));
//                            System.out.println(employee);
//                            employeeList.add(employee);
//                        }
//                        exchange.getIn().setBody(employeeList);
//                    }
//                })
//                .log("Body : ${body}");
    }

}
