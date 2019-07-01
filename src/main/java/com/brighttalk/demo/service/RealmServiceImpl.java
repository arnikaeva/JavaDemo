package com.brighttalk.demo.service;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.exception.ExceptionCode;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;
import com.brighttalk.demo.repository.RealmRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class RealmServiceImpl implements RealmService {

  RealmRepository realmRepository;
  EncryptionService encryptionService;

  public RealmServiceImpl(RealmRepository realmRepository, EncryptionService encryptionService) {
    this.realmRepository = realmRepository;
    this.encryptionService = encryptionService;
  }

  @Override
  public Realm create(RealmRequest request) throws RealmServiceException {
    validate(request);
    Realm realm = convertAndEncrypt(request);

    try {
      realm = realmRepository.save(realm);
      return realm;

    } catch (DataIntegrityViolationException e) {
      if (isDuplicatedNameError(realm)) {
        throw new RealmServiceException(e, ExceptionCode.DUPLICATE_REALM_NAME);
      } else {
        throw new RealmServiceException(e, ExceptionCode.UNKNOWN_ERROR);
      }
    }
  }

  @Override
  public Realm get(int id) throws RealmServiceException {
    Optional<Realm> realm = realmRepository.findById(id);

    if (realm.isPresent()) {
      return realm.get();
    } else {
      throw new RealmServiceException(ExceptionCode.REALM_NOT_FOUND);
    }
  }

  private Realm convertAndEncrypt(RealmRequest request) {
    return new Realm(
        request.getName(),
        request.getDescription(),
        encryptionService.generateRealmKey(request)
    );
  }

  private void validate(RealmRequest request) throws RealmServiceException {
    if (StringUtils.isEmpty(request.getName())) {
      throw new RealmServiceException(ExceptionCode.INVALID_REALM_NAME);
    }
  }

  private boolean isDuplicatedNameError(Realm realm) {
    return realmRepository.existsByName(realm.getName());
  }
}
