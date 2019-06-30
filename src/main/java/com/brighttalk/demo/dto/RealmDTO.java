package com.brighttalk.demo.dto;

public class RealmDTO {
  private long id;

  private String name;

  private String descripton;

  private String key;

  public RealmDTO(String name, String descripton) {
    this.name = name;
    this.descripton = descripton;
  }
}
