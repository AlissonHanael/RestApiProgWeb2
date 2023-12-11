package br.edu.ifpr.api__noticias.repositorio;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifpr.api__noticias.model.Usuario;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

  Optional<Usuario> findByLogin(String login);

  @Modifying
  @Transactional
  @Query(value = "UPDATE usuario SET keytoken = :keytoken WHERE id = :id", nativeQuery = true)
  void updateKey(@Param("keytoken") String chave, @Param("id") long id);

  Optional<Usuario> findBykeytoken(String keytoken);

}
