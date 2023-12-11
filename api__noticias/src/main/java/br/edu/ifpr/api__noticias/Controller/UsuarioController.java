package br.edu.ifpr.api__noticias.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.api__noticias.model.Usuario;
import br.edu.ifpr.api__noticias.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioRepositorio _usuarioRepositorio;
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

  @PostMapping(value = "/cadastro")
  public ResponseEntity<?> criarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (result.hasErrors()) {
      map.put("Status", HttpStatus.BAD_REQUEST.value());
      map.put("Errors", result.getAllErrors());
      return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    } else {

      try {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        Usuario user = _usuarioRepositorio.save(usuario);
        map.put("status", HttpStatus.CREATED.value());
        map.put("data", user);
        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
      } catch (Exception e) {
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("Error", e.getMessage());
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
      }

    }

  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody Usuario usuario) {
    Optional<Usuario> result = _usuarioRepositorio.findByLogin(usuario.getLogin());
    if (result.isPresent()) {
      Usuario user = result.get();
      if (encoder.matches(usuario.getPassword(), user.getPassword())) {
        String keytoken = KeyGenerators.string().generateKey();
        _usuarioRepositorio.updateKey(keytoken, user.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", keytoken);
        return new ResponseEntity<>(map, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
