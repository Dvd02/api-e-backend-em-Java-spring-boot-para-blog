package br.com.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUsuarioIdOrderByIdDesc(Long idUsuario, Pageable pageable);

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    Page<Post> findByTituloContainingIgnoreCaseOrTextoContainingIgnoreCaseOrderByIdDesc(String titulo, String comentario, Pageable pageable);

    Page<Post> findByUsuarioNomeContainingIgnoreCaseOrderByIdDesc(String nomeUsuario, Pageable pageable);
}
