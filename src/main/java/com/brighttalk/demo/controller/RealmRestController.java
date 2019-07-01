package com.brighttalk.demo.controller;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.dto.RealmResponse;
import com.brighttalk.demo.dto.RealmResponseFailure;
import com.brighttalk.demo.dto.RealmResponseSuccess;
import com.brighttalk.demo.exception.ExceptionCode;
import com.brighttalk.demo.exception.RealmServiceException;
import com.brighttalk.demo.model.Realm;
import com.brighttalk.demo.service.RealmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RealmRestController {

  RealmService realmService;

  public RealmRestController(RealmService realmService) {
    this.realmService = realmService;
  }

  @PostMapping(
      value = "service/user/realm",
      consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
      produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<RealmResponse> create(@RequestBody RealmRequest request) throws RealmServiceException {

    Realm realm = realmService.create(request);

    return new ResponseEntity<>(
        new RealmResponseSuccess(realm),
        new HttpHeaders(),
        HttpStatus.OK);
  }

  @GetMapping(
      value = "service/user/realm/{id}",
      produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<RealmResponse> get(@PathVariable(name = "id") int id) throws RealmServiceException {

    Realm realm = realmService.get(id);

    return new ResponseEntity<>(
        new RealmResponseSuccess(realm),
        new HttpHeaders(),
        HttpStatus.OK);
  }

  @ExceptionHandler(RealmServiceException.class)
  public ResponseEntity<RealmResponse> serviceException(RealmServiceException e) {
    return new ResponseEntity<>(
        new RealmResponseFailure(e.getCode()),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<RealmResponse> invalidArgument() {
    return new ResponseEntity<>(
        new RealmResponseFailure(ExceptionCode.INVALID_ARGUMENT),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
