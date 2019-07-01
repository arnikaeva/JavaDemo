package com.brighttalk.demo.dto;

import com.brighttalk.demo.exception.ExceptionCode;

public class RealmResponseFailure extends RealmResponse {

  private String code;

  public RealmResponseFailure(ExceptionCode code) {
    this.code = code.getMessage();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
