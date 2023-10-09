package com.securityexamples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            When demo endpoint is called without authentication we expect a HTTP response of 401 Unauthorized
            """)
    void test1() throws Exception {
        mockMvc.perform(get("/api/demo"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("""
            When demo endpoint is called without authentication but wrong authority we expect a HTTP response of 403 Forbidden
            """)
    @WithMockUser(authorities = "wrong")
    void test2() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("""
            When demo endpoint is called with authentication and correct authority we expect a HTTP response of 200 OK and a response body demo
            """)
    @WithMockUser(authorities = "READ")
    void test3() throws Exception {
        mockMvc.perform(get("/api/demo"))
                .andExpect(status().isOk())
                .andExpect(content().string("demo"));
    }

}
