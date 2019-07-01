package com.brighttalk.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="realm")
public class Realm {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer id;

  @Column(name="realm_name", unique = true)
  private String name;

  @Size(max = 255)
  @Column(name="realm_description")
  private String description;

  @Size(min=32, max=32)
  @Column(name="encryption_key")
  private String key;

  public Realm() {
  }

  public Realm(int id, String name, @Size(max = 255) String description, @Size(min = 32, max = 32) String key) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.key = key;
  }

  public Realm(String name, @Size(max = 255) String description, @Size(min = 32, max = 32) String key) {
    this.name = name;
    this.description = description;
    this.key = key;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
