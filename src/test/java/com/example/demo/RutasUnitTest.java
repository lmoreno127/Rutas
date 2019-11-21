package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import servicelayer.BusinessCaseApplication;
import servicelayer.RutasService;
@SpringBootTest(classes=BusinessCaseApplication.class)

public class RutasUnitTest {
	
	@Autowired
	RutasService rutasService;
	 
	 

	@Test
	public void retornaRuta(){
		String expected="1->4->2->3";
		assertEquals(expected,rutasService.rutaMasCorta(1,3).get("ruta"));

	}
	
	@Test
	public void retornaDistancia() {
		String expected="6";
		assertEquals(expected,rutasService.rutaMasCorta(1,3).get("distancia"));
	}
	@Test
	public void retornaNull() {
		assertNull(rutasService.rutaMasCorta(3, 1));
	}
	
	
}
