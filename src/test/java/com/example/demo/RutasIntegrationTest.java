package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import servicelayer.BusinessCaseApplication;
@SpringBootTest(classes=BusinessCaseApplication.class)

@AutoConfigureMockMvc 
public class RutasIntegrationTest {
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void retornaRutaExistenteJson()  throws Exception{
		mockMvc.perform(get("/?origen=1&destino=3").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.RutaMasCorta", is("1->4->2->3")))
		.andExpect(jsonPath("$.Distancia", is("6")));

	}
	@Test
    public void retornaErrorJson()  throws Exception{
		mockMvc.perform(get("/?origen=2&destino=1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ERROR", is("No hay camino entre 2 y 1")));
	}
}
