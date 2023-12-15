package br.edu.ifpr.api__noticias.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifpr.api__noticias.model.Category;
import br.edu.ifpr.api__noticias.repositorio.CategoryRepository;

public class CategoryController {
  private CategoryRepository _categoryRepository;

  @GetMapping(value = "/category/{id}")
  public ResponseEntity<Category> getNoticia(@PathVariable("id") long id) {
    Optional<Category> category = _categoryRepository.findById(id);
    if (category.isPresent()) {
      return new ResponseEntity<Category>(category.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
