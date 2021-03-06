package com.example.contexthierarchy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AppControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DiceRollService diceRollService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void indexGetShouldReturn200() throws Exception {
        // given
        when(diceRollService.getDistribution(anyInt())).thenReturn(0.0);

        // when
        final ResultActions result = mvc.perform(get("/"));

        // then
        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    public void indexPostShouldRedirect() throws Exception {
        // when
        final ResultActions result = mvc.perform(post("/"));

        // then
        result.andExpect(status().is3xxRedirection());
    }

}
