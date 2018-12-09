package com.example.client.controller;

import com.example.client.config.ResourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
@Import(ResourceConfig.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void publicMethod() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.data").value("publicMethod"))
        ;
    }

    @Test
    public void privateMethod_isUnauthorized() throws Exception {
        mockMvc.perform(get("/private"))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser
    public void privateMethod() throws Exception {

        mockMvc.perform(get("/private"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testRole_user() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty())
        ;

        mockMvc.perform(get("/vip"))
                .andExpect(status().isForbidden())
        ;
    }


    @Test
    @WithMockUser(roles = "VIP")
    public void testRole_vip() throws Exception {
        mockMvc.perform(get("/vip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty())
        ;

        mockMvc.perform(get("/user"))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    @WithMockUser(roles = {"USER", "VIP"})
    public void testRole_admin() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty())
        ;

        mockMvc.perform(get("/vip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty())
        ;
    }


}