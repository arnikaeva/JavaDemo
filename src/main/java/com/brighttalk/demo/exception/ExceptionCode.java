package com.brighttalk.demo.exception;

public enum ExceptionCode {

  INVALID_REALM_NAME("InvalidRealmName"),
  DUPLICATE_REALM_NAME("DuplicateRealmName"),
  INVALID_ARGUMENT("InvalidArgument"),
  REALM_NOT_FOUND("RealmNotFound"),
  UNKNOWN_ERROR("UnknownError");

  private String message;

  ExceptionCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
