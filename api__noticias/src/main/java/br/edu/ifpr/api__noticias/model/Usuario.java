package br.edu.ifpr.api__noticias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotNull(message = "Nome não pode esstar em branco!")
  private String name;
  @NotNull(message = "E-mail não pode estar em branco!")
  private String login;
  @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres!")
  @NotNull(message = "Senha não pode estar em branco!")
  private String password;
  private String bio;
  @Null
  private String keytoken;

  public Usuario() {
  }

  public Usuario(String name, String login, String password, String bio) {
    this.name = name;
    this.login = login;
    this.password = password;
    this.bio = bio;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBio() {
    return this.bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getKeytoken() {
    return this.keytoken;
  }

  public void setKeytoken(String keytoken) {
    this.keytoken = keytoken;
  }

}
