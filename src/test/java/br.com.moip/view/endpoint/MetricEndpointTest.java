package br.com.moip.view.endpoint;

import br.com.moip.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by adercio on 28/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class })
@TestPropertySource(locations="classpath:application.properties")
@WebAppConfiguration
public class MetricEndpointTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void rankingTotalWebhookTest() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.webhookList.*", hasSize(7)));
    }

    @Test
    public void rankingTotalAccessTest() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accessList.*", hasSize(14)));
    }

    @Test
    public void mustHaveGreaterNumberOfAccesses() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accessList[0].url", is("https://eagerhaystack.com")))
                .andExpect(jsonPath("$.accessList[0].count", is(750)));
    }

    @Test
    public void shouldHaveFewerAccesses() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accessList[13].url", is("https://steepBoomerang.me")))
                .andExpect(jsonPath("$.accessList[13].count", is(677)));
    }

    @Test
    public void shouldHaveGreaterWebhook() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.webhookList[0].httpStatus", is(404)))
                .andExpect(jsonPath("$.webhookList[0].count", is(1474)));
    }

    @Test
    public void shouldHaveFewerWebhook() throws Exception {
        this.mockMvc
                .perform(get("/api/metrics/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.webhookList[6].httpStatus", is(204)))
                .andExpect(jsonPath("$.webhookList[6].count", is(1388)));
    }
}
