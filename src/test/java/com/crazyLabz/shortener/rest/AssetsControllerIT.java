package com.crazyLabz.shortener.rest;

import com.crazyLabz.shortener.service.AssetsService;
import com.crazyLabz.shortener.service.ShortenerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestWebConfig.class, ControlTestContext.class})
@WebAppConfiguration
public class AssetsControllerIT {

    private static final String ASSETS_PATH = "/v1/assets";

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private AssetsService assetsService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test
    public void shouldFindAllAssets() throws Exception {
        mockMvc.perform(get(ASSETS_PATH + "/all")).andExpect(status().isOk());
        verify(assetsService).findAll();
    }
}