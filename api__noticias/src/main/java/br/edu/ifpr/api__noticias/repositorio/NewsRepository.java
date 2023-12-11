package br.edu.ifpr.api__noticias.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.api__noticias.model.Noticia;

public interface NewsRepository extends CrudRepository<Noticia, Long> {

}
