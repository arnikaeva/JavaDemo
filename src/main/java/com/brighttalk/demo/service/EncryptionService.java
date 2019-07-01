package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;

public interface EncryptionService {

  String generateRealmKey(RealmRequest request);
}
