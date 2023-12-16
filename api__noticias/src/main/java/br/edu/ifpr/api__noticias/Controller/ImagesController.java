package br.edu.ifpr.api__noticias.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpr.api__noticias.Services.FileStorageService;
import br.edu.ifpr.api__noticias.model.Images;
import br.edu.ifpr.api__noticias.model.Noticia;
import br.edu.ifpr.api__noticias.repositorio.ImagesRepository;

@RestController
public class ImagesController {

  @Autowired
  private FileStorageService _fileStorageService;

  @Autowired
  private ImagesRepository _imagesRepository;

  @PostMapping(value = "/upload/{id}")
  public ResponseEntity<?> upload(@RequestParam("nome") MultipartFile arquivo, @PathVariable("id") Noticia news_id) {

    Map<String, Object> map = new HashMap<String, Object>();
    try {
      _fileStorageService.save(arquivo);
      _imagesRepository.save(new Images(arquivo.getOriginalFilename(), news_id));
      map.put("arquivo", arquivo.getOriginalFilename());
      map.put("news_id", news_id);
      return new ResponseEntity<>(map, HttpStatus.CREATED);
    } catch (Exception e) {
      map.put("erro", e.getMessage());
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
  }

  /*
   * @PostMapping(value = "/upload/{id}")
   * public ResponseEntity<?> upload(@RequestParam("nome") MultipartFile
   * arquivo, @PathVariable("id") Noticia news_id) {
   * Map<String, Object> map = new HashMap<>();
   * try {
   * _fileStorageService.save(arquivo);
   * 
   * // Crie a entidade Images com a associação ao news_id
   * Images imagem = new Images(arquivo.getOriginalFilename(), news_id);
   * _imagesRepository.save(imagem);
   * 
   * map.put("arquivo", arquivo.getOriginalFilename());
   * return new ResponseEntity<>(map, HttpStatus.CREATED);
   * } catch (Exception e) {
   * map.put("erro", e.getMessage());
   * return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
   * }
   * }
   */
  /*
   * @PostMapping(value = "/upload")
   * public Images upload(@RequestBody Images images) {
   * 
   * return _imagesRepository.save(images);
   * 
   * }
   */
}
