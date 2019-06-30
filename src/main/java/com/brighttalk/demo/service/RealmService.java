package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmDTO;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;

public interface RealmService {

  Realm create(RealmDTO dto) throws RealmServiceException;
  Realm get(long id) throws RealmServiceException;

}
