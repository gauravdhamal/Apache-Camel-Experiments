package com.mockito;

import com.mockito.service.ToDoBusiness;
import com.mockito.service.ToDoService;
import org.hamcrest.CoreMatchers;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationExample {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ToDoService toDoService;

    @InjectMocks
    ToDoBusiness toDoBusiness;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Spy
    ArrayList<String> stringArrayList;

    @Test
    public void deleteToUsing_BDD(){

        List<String> combinedList = Arrays.asList("Use Hibernate Java",
                "Use Hibernate Core", "Use Hibernate", "Use Spring MVC");

        BDDMockito.given(toDoService.getToDos("dummy")).willReturn(combinedList);

        stringArrayList.add("Mockito1");
        stringArrayList.add("Mockito2");
        stringArrayList.add("Mockito3");

        toDoBusiness.deleteToDosNotRelatedToHibernate("dummy");

        Mockito.verify(stringArrayList).add("Mockito1");
        Mockito.verify(stringArrayList).add("Mockito2");
        Mockito.verify(stringArrayList).add("Mockito3");

        Mockito.verify(toDoService, Mockito.times(1)).deleteToDos("Use Spring MVC");
        Mockito.verify(toDoService, Mockito.never()).deleteToDos("Use Hibernate Java");
        Mockito.verify(toDoService, Mockito.never()).deleteToDos("Use Hibernate");

        assertEquals(3, stringArrayList.size());

        System.out.println("Working fine.");
    }

    @Test
    public void deleteToDosUsing_BDD_usingArgCaptor(){

        List<String> combinedList = Arrays.asList("Use Hibernate Java",
                "Use Hibernate Core", "Use Hibernate", "Use Spring MVC");

        BDDMockito.given(toDoService.getToDos("dummy")).willReturn(combinedList);

        toDoBusiness.deleteToDosNotRelatedToHibernate("dummy");

        BDDMockito.then(toDoService).should().deleteToDos(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(), CoreMatchers.is("Use Spring MVC"));

        System.out.println("test working f9.");
    }

}
