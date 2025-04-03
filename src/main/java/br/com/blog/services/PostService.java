package br.com.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blog.exceptions.ResourceNotFoundException;
import br.com.blog.model.Post;
import br.com.blog.repositories.PostRepository;
import br.com.blog.utils.PaginacaoUtil;

@Service
public class PostService {
  @Autowired
  PostRepository repository;

  public Page<Post> findAllPostsPaginated(int pagina, int tamanho) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findAllByOrderByIdDesc(pageable);
  }

  public Page<Post> findAllPostsByUsuarioIdPaginated(int pagina, int tamanho, Long idUsuario) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByUsuarioIdOrderByIdDesc(idUsuario, pageable);
  }

  public Page<Post> findAllPostsByUsuarioNomePaginated(int pagina, int tamanho, String nomeUsuario) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByUsuarioNomeContainingIgnoreCaseOrderByIdDesc(nomeUsuario, pageable);
  }

  public Page<Post> findAllPostsByTextPaginated(int pagina, int tamanho, String texto) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByTituloContainingIgnoreCaseOrTextoContainingIgnoreCaseOrderByIdDesc(texto, texto, pageable);
  }
  
  public Post findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O post (ID: " + id + ") não existe."));
  }

  public Post create(Post post) {
    return repository.save(post);
  }
  
  public Post update(Post post) {
    long id = post.getId();

    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O post (ID: " + id + ") não existe."));

    return repository.save(post);
  }

  public void delete(Long id) {
    Post entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O post (ID: " + id + ") não existe."));

    repository.delete(entity);
  }
}
