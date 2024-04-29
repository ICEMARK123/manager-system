package com.ice.manager.system.controller;


import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddUserAccessSuccess() throws Exception {
        mockMvc.perform(post("/admin/addUser")
                        .header("Authorization", "eyJyb2xlIjoiYWRtaW4iLCJhY2NvdW50TmFtZSI6IlhYWFhYWFgiLCJ1c2VySWQiOjEyMzQ1Nn0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 123456, \"endpoints\": [\"resource A\", \"resource B\", \"resource C\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User access added"));
    }

    @Test
    public void testAddUserAccessFail() throws Exception {
        mockMvc.perform(post("/admin/addUser")
                        .header("Authorization", "eyJyb2xlIjoidXNlciIsImFjY291bnROYW1lIjoiWFhYWFhYWCIsInVzZXJJZCI6MTIzNDU2fQ==")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 123456, \"endpoints\": [\"resource A\", \"resource B\", \"resource C\"]}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Unauthorized access"));
    }

}