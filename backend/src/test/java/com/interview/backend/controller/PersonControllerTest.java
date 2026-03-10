package com.interview.backend.controller;
import com.interview.backend.model.Person;
import com.interview.backend.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Test
    void getAllPersons_ShouldReturnJson() throws Exception {
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void getPersonById_ShouldReturnExactJsonFormat() throws Exception {
        Person p = new Person(1L, "Müller", "Hans", "67742", "Lauterecken", "blau");
        when(personService.getPersonById(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hans"))
                .andExpect(jsonPath("$.lastname").value("Müller"))
                .andExpect(jsonPath("$.zipcode").value("67742"));
    }
}