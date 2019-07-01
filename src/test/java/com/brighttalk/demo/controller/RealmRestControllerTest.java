package com.brighttalk.demo.controller;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.service.RealmBuilder;
import com.brighttalk.demo.service.RealmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(RealmRestController.class)
public class RealmRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RealmService realmService;

  @Test
  public void create_success() throws Exception {
    String name = RealmBuilder.DUMMY_NAME;
    String description = RealmBuilder.DUMMY_DESCRIPTION;

    RealmRequest realmRequest = new RealmRequest(name, description);
    when(realmService.create(any()))
        .thenReturn(RealmBuilder.create(name, description));

    this.mockMvc
        .perform(post("/service/user/realm/")
            .content(new ObjectMapper().writeValueAsString(realmRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(RealmBuilder.DUMMY_ID))
        .andExpect(jsonPath("name").value(RealmBuilder.DUMMY_NAME))
        .andExpect(jsonPath("description").value(RealmBuilder.DUMMY_DESCRIPTION))
        .andExpect(jsonPath("key").value(RealmBuilder.DUMMY_KEY));
  }

  @Test
  public void get_success() throws Exception {
    int id = RealmBuilder.DUMMY_ID;

    when(realmService.get(id))
        .thenReturn(RealmBuilder.create(id));

    this.mockMvc
        .perform(get("/service/user/realm/" + RealmBuilder.DUMMY_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(RealmBuilder.DUMMY_ID))
        .andExpect(jsonPath("name").value(RealmBuilder.DUMMY_NAME))
        .andExpect(jsonPath("description").value(RealmBuilder.DUMMY_DESCRIPTION))
        .andExpect(jsonPath("key").value(RealmBuilder.DUMMY_KEY));
  }
}