package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmDTO;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;
import com.brighttalk.demo.repository.RealmRepository;
import org.springframework.stereotype.Service;

@Service
public class RealmServiceImpl implements RealmService {

  RealmRepository realmRepository;

  public RealmServiceImpl(RealmRepository realmRepository) {
    this.realmRepository = realmRepository;
  }

  @Override
  public Realm create(RealmDTO dto) throws RealmServiceException {
    return null;
  }

  @Override
  public Realm get(long id) throws RealmServiceException {
    return null;
  }
}
