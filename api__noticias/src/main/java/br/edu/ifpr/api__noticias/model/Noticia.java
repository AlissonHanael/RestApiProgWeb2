package br.edu.ifpr.api__noticias.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Noticia {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String news_abstract;
  private String news_text;
  private Date pub_date = new Date();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_category")
  private Category category;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_usuario") // Corrija para o nome correto da coluna de relacionamento
  private Usuario author;

  private int counter;

  public Noticia() {
  }

  // Ajuste o construtor para receber o nome do arquivo
  public Noticia(String title, String news_abstract, String news_text, Category category, Usuario author,
      String archive_name, String directory, int counter) {
    this.title = title;
    this.news_abstract = news_abstract;
    this.news_text = news_text;
    this.category = category; // Ajuste para aceitar uma categoria como parâmetro
    this.author = author;
    this.counter = counter;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNews_abstract() {
    return this.news_abstract;
  }

  public void setNews_abstract(String news_abstract) {
    this.news_abstract = news_abstract;
  }

  public String getNews_text() {
    return this.news_text;
  }

  public void setNews_text(String news_text) {
    this.news_text = news_text;
  }

  public Date getPub_date() {
    return this.pub_date;
  }

  public void setPub_date(Date pub_date) {
    this.pub_date = pub_date;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Usuario getAuthor() {
    return this.author;
  }

  public void setAuthor(Usuario author) {
    this.author = author;
  }

  public int getCounter() {
    return this.counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

}
