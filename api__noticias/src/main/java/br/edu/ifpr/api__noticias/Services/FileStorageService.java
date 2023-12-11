package br.edu.ifpr.api__noticias.Services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  public void init();

  public void save(MultipartFile arquivo);

  public Resource load(String nomeArquivo);

  public void deleteAll();

  public Stream<Path> loadAll();
}
