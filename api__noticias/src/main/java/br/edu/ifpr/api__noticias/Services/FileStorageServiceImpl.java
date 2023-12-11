package br.edu.ifpr.api__noticias.Services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final Path root = Paths.get("upload");

  @Override
  public void init() {
    try {
      Files.createDirectories(root);
    } catch (Exception e) {
      throw new RuntimeException("Não foi possível criar a pasta para upload: " + e.getMessage());
    }
  }

  @Override
  public void save(MultipartFile arquivo) {
    try {
      Files.copy(arquivo.getInputStream(), this.root.resolve(arquivo.getOriginalFilename()));
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("Arquivo já existe");
      }
      throw new RuntimeException("Não foi possível salvar o arquivo: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String nomeArquivo) {
    try {
      Path arquivo = this.root.resolve(nomeArquivo);
      Resource resource = new UrlResource(arquivo.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Não foi possível ler o arquivo/o aquivo não existe");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Erro: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(this.root.toFile());

  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Não foi possível ler os arquivos");
    }
  }

}
