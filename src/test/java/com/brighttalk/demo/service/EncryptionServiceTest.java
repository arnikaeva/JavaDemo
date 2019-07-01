package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import org.junit.Before;
import org.junit.Test;

import static com.brighttalk.demo.service.EncryptionServiceImpl.KEY_LENGTH;
import static org.junit.Assert.*;

public class EncryptionServiceTest {

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