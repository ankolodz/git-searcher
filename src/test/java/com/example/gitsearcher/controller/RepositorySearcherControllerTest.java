package com.example.gitsearcher.controller;

import com.example.gitsearcher.service.RepositorySearcherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RepositorySearcherController.class)
public class RepositorySearcherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RepositorySearcherService service;

    @Test
    void shouldReturn406WhenXmlHeader() throws Exception {
        mvc.perform(get("/api/search")
                        .contentType("application/xml"))
                .andExpect(status().is(406));
    }

    @Test
    void shouldReturn200WhenJsonHeader() throws Exception {
        when(service.getRepositories(any())).thenReturn(List.of());
        mvc.perform(get("/api/search?username=ankolodz")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenOtherContentType() throws Exception {
        mvc.perform(get("/api/search?username=ankolodz")
                        .contentType("text/html"))
                .andExpect(status().is(500));
    }
}
