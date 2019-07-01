package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.model.Realm;

public class RealmBuilder {
  public final static int DUMMY_ID = 1;
  public final static String DUMMY_NAME = "dummyName";
  public final static String DUMMY_DESCRIPTION = "dummyDescription";
  public final static String DUMMY_KEY = "dummyDescription";

  public static Realm createModel(int id) {
    return new Realm(id, DUMMY_NAME, DUMMY_DESCRIPTION, DUMMY_KEY);
  }

  public static Realm createModel(String name, String description) {
    return new Realm(DUMMY_ID, name, description, DUMMY_KEY);
  }

  public static RealmRequest createRequest() {
    RealmRequest realmRequest = new RealmRequest();
    realmRequest.setName(DUMMY_NAME);
    realmRequest.setDescription(DUMMY_DESCRIPTION);
    return realmRequest;
  }

}
