package br.edu.ifpr.api__noticias.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.api__noticias.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
