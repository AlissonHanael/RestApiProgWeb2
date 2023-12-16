package br.edu.ifpr.api__noticias.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.api__noticias.model.Noticia;
import jakarta.transaction.Transactional;

public interface NewsRepository extends CrudRepository<Noticia, Long> {

  @Modifying
  @Transactional
  @Query(value = "UPDATE noticia SET counter = counter + 1 WHERE id = :id", nativeQuery = true)
  void updateCounter(@Param("id") long id);

  Optional<Noticia> findById(long id);

}
