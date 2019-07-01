package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;

public interface RealmService {

  Realm create(RealmRequest request) throws RealmServiceException;
  Realm get(int id) throws RealmServiceException;

}
