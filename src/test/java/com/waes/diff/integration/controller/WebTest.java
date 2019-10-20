package com.waes.diff.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.diff.SpringContextTest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebAppConfiguration
public abstract class WebTest extends SpringContextTest {

    @Autowired
    private WebApplicationContext webContext;

    @Getter(lazy = true, value = AccessLevel.PUBLIC)
    private final MockMvc mvc = MockMvcBuilders.webAppContextSetup(webContext).build();

    @SneakyThrows
    protected String toJson(final Object object) {
        return new ObjectMapper().writeValueAsString(object);
    }

}
