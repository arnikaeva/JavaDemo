package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionServiceTest {

  static final int KEY_LENGTH = 32;

  EncryptionService encryptionService;

  @Before
  public void setUp() {
    encryptionService = new EncryptionServiceImpl();
  }

  @Test
  public void generateRealmKey_success() {
    RealmRequest realmRequest = new RealmRequest(RealmBuilder.DUMMY_NAME, RealmBuilder.DUMMY_DESCRIPTION);

    String key = encryptionService.generateRealmKey(realmRequest);

    assertNotNull(key);
    assertEquals(KEY_LENGTH, key.length());
  }




}