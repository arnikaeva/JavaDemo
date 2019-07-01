package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

  public final static String DUMMY_KEY = "AIA1esBlFEUzlSyBHCY4uWpyK6deweCh";

  /* Dummy implementation for key generation.
     Key has a fixed length 32 characters.
  */
  @Override
  public String generateRealmKey(RealmRequest request) {
    return DUMMY_KEY;
  }
}
