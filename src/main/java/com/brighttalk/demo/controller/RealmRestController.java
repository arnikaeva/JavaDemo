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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RealmRestController {

  RealmService realmService;
  final Logger logger = LoggerFactory.getLogger(RealmRestController.class);

  public RealmRestController(RealmService realmService) {
    this.realmService = realmService;
  }

  @PostMapping("service/user/realm")
  public ResponseEntity<RealmResponse> create(@RequestBody RealmRequest request) {
    RealmResponse response;
    HttpStatus httpStatus;

    try {
      Realm realm = realmService.create(request);
      response = new RealmResponseSuccess(realm);
      httpStatus = HttpStatus.OK;

    } catch (RealmServiceException e) {
      logger.error(e.getMessage());
      response = new RealmResponseFailure(e.getCode());
      httpStatus = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
  }

  @GetMapping("service/user/realm/{id}")
  public ResponseEntity<RealmResponse> get(@PathVariable(name = "id") int id) {
    RealmResponse response;
    HttpStatus httpStatus;

    try {
      Realm realm = realmService.get(id);
      response = new RealmResponseSuccess(realm);
      httpStatus = HttpStatus.OK;

    } catch (RealmServiceException e) {
      logger.error(e.getMessage());
      response = new RealmResponseFailure(e.getCode());
      httpStatus = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<RealmResponse> invalidArgument() {
    return new ResponseEntity<>(
        new RealmResponseFailure(ExceptionCode.INVALID_ARGUMENT),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
