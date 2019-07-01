package com.brighttalk.demo.exception;

public class RealmServiceException extends Exception {

  private ExceptionCode code;

  public RealmServiceException(ExceptionCode code) {
    this.code = code;
  }

  public RealmServiceException(Throwable cause, ExceptionCode code) {
    super(cause);
    this.code = code;
  }

  public ExceptionCode getCode() {
    return code;
  }
}
