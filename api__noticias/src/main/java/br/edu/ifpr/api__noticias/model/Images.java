package br.edu.ifpr.api__noticias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
public class Images {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private String nome;
  @Null
  @Transient
  private String caminho;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "news_id")
  private Noticia news_id;

  public Images(String nome, Noticia news_id) {

    this.news_id = news_id;
    this.nome = nome;
  }

  public Images(long id, String nome, Noticia news_id) {
    this.id = id;
    this.nome = nome;
    this.news_id = news_id;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCaminho() {
    return this.caminho;
  }

  public void setCaminho(String caminho) {
    this.caminho = caminho;
  }

  public Noticia getNews_id() {
    return this.news_id;
  }

  public void setNews_id(Noticia news_id) {
    this.news_id = news_id;
  }

}
