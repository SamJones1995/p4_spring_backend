package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestControllerTest {
	
	
	//Unit Test
	@Test
	void hello() {
		TestController controller = new TestController(); //Arange
		String response = controller.hello("World"); //act
		assertEquals("Hello World!", response); //assert
	}

}
