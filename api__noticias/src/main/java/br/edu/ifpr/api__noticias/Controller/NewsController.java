package br.edu.ifpr.api__noticias.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifpr.api__noticias.model.Noticia;
import br.edu.ifpr.api__noticias.repositorio.NewsRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/v1")
public class NewsController {

  @Autowired
  private NewsRepository _NewsRepository;

  @GetMapping(value = "/news")
  public List<Noticia> get() {
    return (List<Noticia>) _NewsRepository.findAll();
  }

  @GetMapping(value = "/news/{id}")
  public ResponseEntity<Noticia> getNoticia(@PathVariable("id") long id) {
    Optional<Noticia> noticia = _NewsRepository.findById(id);
    Noticia result = noticia.get();
    if (noticia.isPresent()) {
      _NewsRepository.updateCounter(result.getId());
      return new ResponseEntity<Noticia>(noticia.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
  }

  @DeleteMapping(value = "/news/{id}")
  public ResponseEntity<?> deleteNoticia(@PathVariable("id") long id) {
    Optional<Noticia> noticia = _NewsRepository.findById(id);
    if (noticia.isPresent()) {
      _NewsRepository.delete(noticia.get());
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("news/{id}")
  public ResponseEntity<Noticia> updateNews(@PathVariable("id") long id, @RequestBody Noticia newNotices) {
    Optional<Noticia> outdatedNews = _NewsRepository.findById(id);
    if (outdatedNews.isPresent()) {
      Noticia news = outdatedNews.get();
      news.setTitle(newNotices.getTitle());
      _NewsRepository.save(news);
      return new ResponseEntity<Noticia>(news, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/news")
  public ResponseEntity<?> createNews(@RequestBody @Valid Noticia noticia, BindingResult result) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (result.hasErrors()) {
      map.put("status", HttpStatus.BAD_REQUEST.value());
      map.put("errors", result.getAllErrors());
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    } else {
      try {
        Noticia savedNoticia = _NewsRepository.save(noticia);
        map.put("status", HttpStatus.CREATED.value());
        map.put("data", savedNoticia);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
      } catch (Exception e) {
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("error", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
      }
    }
  }

}
