package com.brighttalk.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Realm {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @Size(max = 255)
  private String description;

  @Size(min=32, max=32)
  private String key;

}
