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
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testCheckUserSuccess() throws Exception {
        ReentrantLock log = new ReentrantLock(true);
        mockMvc.perform(get("/user/resource A")
                        .header("Authorization", "eyJyb2xlIjoiYWRtaW4iLCJhY2NvdW50TmFtZSI6IlhYWFhYWFgiLCJ1c2VySWQiOjEyMzQ1Nn0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Access granted"));
    }

    @Test
    public void testCheckUserAccessFail() throws Exception {
        mockMvc.perform(get("/user/resource D")
                        .header("Authorization", "eyJyb2xlIjoiYWRtaW4iLCJhY2NvdW50TmFtZSI6IlhYWFhYWFgiLCJ1c2VySWQiOjEyMzQ1Nn0"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Access denied"));
    }

}