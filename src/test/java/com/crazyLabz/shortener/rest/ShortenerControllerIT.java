package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.requests.ShortenRequest;
import com.crazyLabz.shortener.service.ShortenerService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import test.com.crazyLabz.shortener.config.ControlTestContext;
import test.com.crazyLabz.shortener.config.TestWebConfig;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestWebConfig.class, ControlTestContext.class})
@WebAppConfiguration
public class ShortenerControllerIT {

    private static final String SHORTENER_PATH = "/v1/short";
    private static final String URL = "http://short-me.com";
    private static final String PREFIX = "http://crazyLabz.com";
    private static final int SUFFIX_LENGTH = 8;
    private static final ShortenRequest SHORTEN_REQUEST = ShortenRequest.builder().url(URL).prefix(PREFIX).suffixLength(SUFFIX_LENGTH).build();

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ShortenerService shortenerService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        mockMvc.perform(post(SHORTENER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(SHORTEN_REQUEST)))
                .andExpect(status().isOk());

        verify(shortenerService).shorten(URL, PREFIX, SUFFIX_LENGTH);
    }

}