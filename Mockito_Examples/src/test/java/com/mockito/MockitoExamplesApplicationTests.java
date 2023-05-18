package com.mockito;

import com.mockito.service.ToDoBusiness;
import com.mockito.service.ToDoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.Test;	// This is used in JUnit version 4.
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MockitoExamplesApplicationTests {

	@Test
	public void testUsingMocks(){
		// Creating dummy mock
		ToDoService toDoServiceMock = Mockito.mock(ToDoService.class);

		// Creating dummy list.
		List<String> combinedList = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Use Hibernate ", " Use Spring MVC ");

		// Stubbing
		Mockito.when(toDoServiceMock.getToDos("dummy")).thenReturn(combinedList);

		// Creating business object and resolving dependency.
		ToDoBusiness toDoBusiness = new ToDoBusiness(toDoServiceMock);

		// Calling method business object.
		List<String> list = toDoBusiness.getToDosForSpring("dummy");

		System.out.println(list);

		// Checking
		Assertions.assertEquals(1, list.size());
	}

	// Mocking list class
	@Test
	public void listMocking(){
		// Mocking list object.
		List mockList = Mockito.mock(List.class);

		// Stubbing
		Mockito.when(mockList.size()).thenReturn(1);

		// Checking
		Assertions.assertEquals(1, mockList.size());

//		Assert.assertEquals(2, mockList.size());

	}

	// Return multiple values from list
	@Test
	public void multipleListValues(){
		List mockList = Mockito.mock(List.class);

		Mockito.when(mockList.size()).thenReturn(1).thenReturn(2).thenReturn(3);

		Assertions.assertEquals(1, mockList.size());
		Assertions.assertEquals(2, mockList.size());
		Assertions.assertEquals(3, mockList.size());

		System.out.println("mockList.size() : "+mockList.size());

		System.out.println("mockList : "+mockList);
	}

	@Test
	public void checkGetInList(){
		List mockList = Mockito.mock(List.class);

		Mockito.when(mockList.get(0)).thenReturn("Mockito");

		Assertions.assertEquals("Mockito", mockList.get(0));

		System.out.println("mockList.get(0) : "+mockList.get(0));
	}

	@Test
	public void deleteToDoUsing_BDD(){
		ToDoService toDoService = Mockito.mock(ToDoService.class);

		List<String> list = Arrays.asList("Use Hibernate Java", "Use Hibernate Core", "Use Hibernate", "Use Spring MVC");

		BDDMockito.given(toDoService.getToDos("dummy")).willReturn(list);

		ToDoBusiness toDoBusiness = new ToDoBusiness(toDoService);

		toDoBusiness.deleteToDosNotRelatedToHibernate("dummy");

		verify(toDoService).deleteToDos("Use Spring MVC");
	}

	@Test
	public void spyTest(){
		List spyList = spy(ArrayList.class);

		Assertions.assertEquals(0, spyList.size());

		spyList.add("Mock");

		Assertions.assertEquals(1, spyList.size());
	}
}
