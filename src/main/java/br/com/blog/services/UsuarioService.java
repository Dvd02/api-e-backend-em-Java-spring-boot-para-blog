package br.com.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blog.exceptions.AuthenticationFailedException;
import br.com.blog.exceptions.ResourceNotFoundException;
import br.com.blog.model.Usuario;
import br.com.blog.repositories.UsuarioRepository;
import br.com.blog.utils.PaginacaoUtil;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository repository;

  public Usuario authenticate(String email, String senha) {
    Usuario usuario = repository.findByEmail(email)
        .orElseThrow(() -> new AuthenticationFailedException("Usuário não encontrado com o email: " + email));

    if (!usuario.getSenha().equals(senha)) {
      throw new AuthenticationFailedException("Senha incorreta");
    }

    return usuario;
  }
  
  public List<Usuario> findAll() {
    return repository.findAll();
  }
  
  public Usuario create(Usuario usuario) {
    return repository.save(usuario);
  }

  public Page<Usuario> findAllUsuarioByNomePaginated(int pagina, int tamanho, String nomeUsuario) {
    Pageable pageable = PaginacaoUtil.criarPageable(pagina, tamanho);
    return repository.findByNomeContainingIgnoreCaseOrderByIdDesc(nomeUsuario, pageable);
  }

  public Usuario findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O usuario (ID:"+ id +") não existe"));
  }

  public Usuario update(Usuario usuario) {
    long id = usuario.getId();

    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O usuario (ID:"+ id +") não existe"));

    return repository.save(usuario);
  }

  public void delete(Long id) {
    Usuario entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O usuario (ID:"+ id +") não existe"));

    repository.delete(entity);
  }
}
