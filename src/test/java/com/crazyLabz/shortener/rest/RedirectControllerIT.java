package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.service.RedirectService;
import com.crazyLabz.shortener.service.UrlStatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import test.com.crazyLabz.shortener.config.ControlTestContext;
import test.com.crazyLabz.shortener.config.TestWebConfig;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestWebConfig.class, ControlTestContext.class})
@WebAppConfiguration
public class RedirectControllerIT {

    public static final String ID = "sh0rt";

    public static final String SHORT_URL = "http://www.crazyLabz/" + ID;
    public static final String ORIG_URL = "http://www.google.com";
    public static final String STATS = "/stats";
    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private RedirectService redirectService;

    @Autowired
    private UrlStatsService statsService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test
    public void shouldRedirectUrl() throws Exception {
        when(redirectService.redirect(ID)).thenReturn(ORIG_URL);
        mockMvc.perform(get(SHORT_URL)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl(ORIG_URL));
        verify(redirectService).redirect(ID);
    }

    @Test
    public void shouldRetrieveStats() throws Exception {
        mockMvc.perform(get(SHORT_URL + STATS)).andExpect(status().isOk());
        verify(statsService).stats(ID);
    }
}