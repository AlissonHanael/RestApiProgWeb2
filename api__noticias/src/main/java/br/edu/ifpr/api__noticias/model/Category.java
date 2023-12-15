package br.edu.ifpr.api__noticias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String description;

  public Category() {

  }

  public Category(String description) {
    this.description = description;
  }

  public Category(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}