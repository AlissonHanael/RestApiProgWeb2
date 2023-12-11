package br.edu.ifpr.api__noticias.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
public class Noticia {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String title;
  private String news_abstract;
  private String news_text;
  private LocalDate pub_date;
  private String category;

  @ManyToOne
  @JoinColumn(name = "usuario_id") // Corrija para o nome correto da coluna de relacionamento
  private Usuario author;

  @NotNull
  private String archive_name;

  @Null
  @Transient
  private String directory;
  private int counter;

  public Noticia() {
  }

  // Ajuste o construtor para receber o nome do arquivo
  public Noticia(String title, String news_abstract, String news_text, String category, Usuario author,
      String archive_name, String directory, int counter) {
    this.title = title;
    this.news_abstract = news_abstract;
    this.news_text = news_text;
    this.pub_date = LocalDate.now();
    this.category = "Categoria Pré-definida"; // Ajuste para aceitar uma categoria como parâmetro
    this.author = author;
    this.archive_name = archive_name;
    this.directory = directory;
    this.counter = counter;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
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

  public LocalDate getPub_date() {
    return this.pub_date;
  }

  public void setPub_date(LocalDate pub_date) {
    this.pub_date = pub_date;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Usuario getAuthor() {
    return this.author;
  }

  public void setAuthor(Usuario author) {
    this.author = author;
  }

  public String getArchive_name() {
    return this.archive_name;
  }

  public void setArchive_name(String archive_name) {
    this.archive_name = archive_name;
  }

  public String getDirectory() {
    return this.directory;
  }

  public void setDirectory(String directory) {
    this.directory = directory;
  }

  public int getCounter() {
    return this.counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

}
