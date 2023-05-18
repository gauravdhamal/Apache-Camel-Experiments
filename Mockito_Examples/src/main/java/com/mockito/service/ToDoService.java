package com.mockito.service;

import java.util.List;

public interface ToDoService {

    public List<String> getToDos(String user);

    public void deleteToDos(String user);

}
