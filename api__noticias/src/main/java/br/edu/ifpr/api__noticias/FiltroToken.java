package br.edu.ifpr.api__noticias;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpr.api__noticias.model.Usuario;
import br.edu.ifpr.api__noticias.repositorio.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroToken extends OncePerRequestFilter {

  @Autowired
  private UsuarioRepositorio _usuarioRepositorio;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token;

    var authenticationHeader = request.getHeader("Authorization");
    if (authenticationHeader != null) {
      token = authenticationHeader;
      Optional<Usuario> usuario = this._usuarioRepositorio.findBykeytoken(token);
      if (usuario.isPresent()) {
        var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        response.setStatus(HttpStatus.FORBIDDEN.value());
      }

    }

    filterChain.doFilter(request, response);
  }

}
