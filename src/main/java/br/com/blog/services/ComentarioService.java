package br.com.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blog.exceptions.ResourceNotFoundException;
import br.com.blog.model.Comentario;
import br.com.blog.repositories.ComentarioRepository;
import br.com.blog.utils.PaginacaoUtil;

@Service
public class ComentarioService {

  @Autowired
  ComentarioRepository repository;

  public Page<Comentario> findAllComentariosPaginated(int pagina, int tamanho) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findAllByOrderByIdDesc(pageable);
  }

  public Page<Comentario> findAllComentariosByPostIdPaginated(int pagina, int tamanho, Long idPost) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByPostIdOrderByIdDesc(idPost, pageable);
  }

  public Page<Comentario> findAllComentariosByUsuarioIdPaginated(int pagina, int tamanho, Long idUsuario) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByUsuarioIdOrderByIdDesc(idUsuario, pageable);
  }

  public Page<Comentario> findAllComentariosByUsuarioNomePaginated(int pagina, int tamanho, String nomeUsuario) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByUsuarioNomeContainingIgnoreCaseOrderByIdDesc(nomeUsuario, pageable);
  }

  public Page<Comentario> findAllComentariosByTextPaginated(int pagina, int tamanho, String texto) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByTituloContainingIgnoreCaseOrTextoContainingIgnoreCaseOrderByIdDesc(texto, texto, pageable);
  }

  public Comentario findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O comentario (ID:" + id + ") não existe"));
  }

  public Comentario create(Comentario comentario) {
    return repository.save(comentario);
  }

  public Comentario update(Comentario comentario) {
    Long id = comentario.getId();

    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O comentario (ID:" + id + ") não existe"));

    return repository.save(comentario);
  }

  public void delete(Long id) {
    Comentario entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O comentario (ID:" + id + ") não existe"));

    repository.delete(entity);
  }
}
