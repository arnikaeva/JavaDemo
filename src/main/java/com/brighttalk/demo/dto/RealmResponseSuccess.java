package com.brighttalk.demo.dto;

import com.brighttalk.demo.model.Realm;

public class RealmResponseSuccess extends RealmResponse {

  private int id;

  private String name;

  private String description;

  private String key;

  public RealmResponseSuccess(int id, String name, String description, String key) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.key = key;
  }

  public RealmResponseSuccess(Realm realm) {
    this.id = realm.getId();
    this.name = realm.getName();
    this.description = realm.getDescription();
    this.key = realm.getKey();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
