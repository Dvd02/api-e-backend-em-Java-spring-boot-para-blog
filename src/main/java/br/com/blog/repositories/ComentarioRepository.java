package br.com.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Page<Comentario> findAllByOrderByIdDesc(Pageable pageable);

    Page<Comentario> findByPostIdOrderByIdDesc(Long idPost, Pageable pageable);

    Page<Comentario> findByUsuarioIdOrderByIdDesc(Long idUsuario, Pageable pageable);

    Page<Comentario> findByTituloContainingIgnoreCaseOrTextoContainingIgnoreCaseOrderByIdDesc(String titulo, String comentario, Pageable pageable);

    Page<Comentario> findByUsuarioNomeContainingIgnoreCaseOrderByIdDesc(String nomeUsuario, Pageable pageable);
}
