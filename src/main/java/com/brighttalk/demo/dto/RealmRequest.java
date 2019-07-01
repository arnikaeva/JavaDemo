package com.brighttalk.demo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "realm")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealmRequest {

  @XmlAttribute
  private String name;

  private String description;

  public RealmRequest() {
  }

  public RealmRequest(String name, String description) {
    this.name = name;
    this.description = description;
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

  @Override
  public String toString() {
    return "RealmRequest{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
