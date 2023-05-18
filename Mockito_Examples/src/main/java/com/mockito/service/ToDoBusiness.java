package com.mockito.service;

import java.util.ArrayList;
import java.util.List;

public class ToDoBusiness {

    public ToDoService toDoService;

    public ToDoBusiness(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    public List<String> getToDosForSpring(String user){
        List<String> retrievedToDos = new ArrayList<>();
        List<String> todos  = toDoService.getToDos(user);

        for(String str : todos){
            if(str.contains("Hibernate")){
                retrievedToDos.add(str);
            }
        }
        return retrievedToDos;
    }

    public void deleteToDosNotRelatedToHibernate(String user){
        List<String> list = toDoService.getToDos(user);
        System.out.println("Printing list in deleteToDosNotRelatedToHibernate : "+list);

        for(String str : list){
            if(!str.contains("Hibernate")){
                toDoService.deleteToDos(str);
                System.out.println("Printing input para : "+str);
            }
        }

        System.out.println(list);
     }

}
