package com.brighttalk.demo.dto;

import com.brighttalk.demo.exception.ExceptionCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealmResponseFailure extends RealmResponse {

  private String code;

  public RealmResponseFailure() {
  }

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
