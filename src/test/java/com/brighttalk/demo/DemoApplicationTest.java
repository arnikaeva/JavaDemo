package com.brighttalk.demo;

import com.brighttalk.demo.controller.RealmRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

  @Autowired
  private RealmRestController realmRestController;

  @Test
  public void contextLoads() throws Exception {
    assertNotNull(realmRestController);
  }

}