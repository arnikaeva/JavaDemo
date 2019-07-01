package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.exception.ExceptionCode;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;
import com.brighttalk.demo.repository.RealmRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RealmServiceTest {

  RealmService realmService;

  @Mock
  RealmRepository realmRepository;

  @Mock
  EncryptionService encryptionService;


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    realmService = new RealmServiceImpl(realmRepository, encryptionService);
  }

  @Test
  public void create_success() throws RealmServiceException {
    String name = RealmBuilder.DUMMY_NAME;
    String description = RealmBuilder.DUMMY_DESCRIPTION;
    ArgumentCaptor<Realm> captor = ArgumentCaptor.forClass(Realm.class);
    RealmRequest realmRequest = new RealmRequest(name, description);
    Realm expected = RealmBuilder.createModel(name, description);

    when(encryptionService.generateRealmKey(realmRequest)).thenReturn(RealmBuilder.DUMMY_KEY);
    when(realmRepository.save(captor.capture())).thenReturn(expected);

    Realm actual = realmService.create(realmRequest);
    Realm capturedRealm = captor.getValue();

    assertNull(capturedRealm.getId());
    assertEquals(expected.getName(), capturedRealm.getName());
    assertEquals(expected.getKey(), capturedRealm.getKey());
    assertEquals(expected.getDescription(), capturedRealm.getDescription());

    assertFieldsEquals(expected, actual);
  }

  @Test(expected = RealmServiceException.class)
  public void create_invalidRealmName_emptyString() throws RealmServiceException {
    RealmRequest realmRequest = new RealmRequest("", RealmBuilder.DUMMY_DESCRIPTION);

    try {
      realmService.create(realmRequest);
    } catch (RealmServiceException e) {
      assertEquals(ExceptionCode.INVALID_REALM_NAME, e.getCode());
      throw e;
    }
  }

  @Test(expected = RealmServiceException.class)
  public void create_invalidRealmName_null() throws RealmServiceException {
    RealmRequest realmRequest = new RealmRequest(null, RealmBuilder.DUMMY_DESCRIPTION);

    try {
      realmService.create(realmRequest);
    } catch (RealmServiceException e) {
      assertEquals(ExceptionCode.INVALID_REALM_NAME, e.getCode());
      throw e;
    }
  }

  @Test(expected = RealmServiceException.class)
  public void create_duplicateRealmName() throws RealmServiceException {
      String name = RealmBuilder.DUMMY_NAME;
      String description = RealmBuilder.DUMMY_DESCRIPTION;
      RealmRequest realmRequest = new RealmRequest(name, description);

      when(encryptionService.generateRealmKey(realmRequest)).thenReturn(RealmBuilder.DUMMY_KEY);
      when(realmRepository.save(any())).thenThrow(new DataIntegrityViolationException("Database error"));
      when(realmRepository.existsByName(RealmBuilder.DUMMY_NAME)).thenReturn(true);

      try {
        realmService.create(realmRequest);
      } catch (RealmServiceException e) {
        assertEquals(ExceptionCode.DUPLICATE_REALM_NAME, e.getCode());
        throw e;
      }
  }

  @Test
  public void get_success() throws RealmServiceException {
    int id = RealmBuilder.DUMMY_ID;
    Realm expected = RealmBuilder.createModel(id);

    when(realmRepository.findById(id)).thenReturn(Optional.of(expected));

    Realm actual = realmService.get(id);

    assertNotNull(actual);
    assertFieldsEquals(expected, actual);
  }

  @Test(expected = RealmServiceException.class)
  public void get_notFound() throws RealmServiceException {
    int id = RealmBuilder.DUMMY_ID;

    when(realmRepository.findById(id)).thenReturn(Optional.empty());

    try {
      realmService.get(id);
    } catch (RealmServiceException e) {
      assertEquals(ExceptionCode.REALM_NOT_FOUND, e.getCode());
      throw e;
    }
  }

  private void assertFieldsEquals(Realm expected, Realm actual) {
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getDescription(), actual.getDescription());
    assertEquals(expected.getKey(), actual.getKey());
  }

}