package com.mockito;


import com.mockito.service.ToDoBusiness;
import com.mockito.service.ToDoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ArgumentCaptorExample {

    @Test
    public void deleteToDoUsing_BDD_ArgumentCaptor(){

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        ToDoService toDoService = Mockito.mock(ToDoService.class);

        List<String> list = Arrays.asList("Use Hibernate Java","Use Hibernate Core", "Use Hibernate", "Use Spring MVC");

        BDDMockito.given(toDoService.getToDos("dummy")).willReturn(list);

        ToDoBusiness toDoBusiness = new ToDoBusiness(toDoService);

        toDoBusiness.deleteToDosNotRelatedToHibernate("dummy");

        BDDMockito.then(toDoService).should().deleteToDos(stringArgumentCaptor.capture());

        Assertions.assertThat(stringArgumentCaptor.getValue()).isEqualTo("Use Spring MVC");
//        Assertions.assertThat(stringArgumentCaptor.getAllValues()).hasSize(2);  // getting error

    }

}
