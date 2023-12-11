package br.edu.ifpr.api__noticias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Config {

  @Autowired
  private FiltroToken filtro_token;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf((csrf) -> csrf.disable())
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/usuario/cadastro").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/news").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/news/**").permitAll().anyRequest().authenticated())
        .addFilterBefore(filtro_token, UsernamePasswordAuthenticationFilter.class).build();
  }

}
