package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

  public static final int KEY_LENGTH = 32;

  /* Dummy implementation for key generation.
         Key has a fixed length 32 characters.
       */
  @Override
  public String generateRealmKey(RealmRequest request) {
    StringBuilder sb = new StringBuilder();
    sb.append(request.getName());

    while (sb.length() < KEY_LENGTH) {
      sb.append(request.getName());
    }

    return sb.substring(0, KEY_LENGTH);
  }
}
