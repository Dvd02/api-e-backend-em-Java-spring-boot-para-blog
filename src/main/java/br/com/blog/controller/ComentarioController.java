package br.com.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.exceptions.PermissionDeniedException;
import br.com.blog.mapper.DozerMapper;
import br.com.blog.mapper.curtom.ComentarioDO;
import br.com.blog.model.Comentario;
import br.com.blog.model.Post;
import br.com.blog.model.Usuario;
import br.com.blog.requests.ComentarioRequest;
import br.com.blog.requests.DeleteRequest;
import br.com.blog.response.PaginacaoResponse;
import br.com.blog.response.ResultadoResponse;
import br.com.blog.services.ComentarioService;
import br.com.blog.services.PostService;
import br.com.blog.services.UsuarioService;
import br.com.blog.utils.PaginacaoUtil;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PostService postService;

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<ComentarioDO> findComentariosByUsuarioNomeWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("nomeUsuario") String nomeUsuario
    ) {
        Page<Comentario> postPage = comentarioService.findAllComentariosByUsuarioNomePaginated(pagina, tamanho, nomeUsuario);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, ComentarioDO.class, pagina, tamanho);
    }
    
    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/do_post_id/{idPost}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<ComentarioDO> findComentariosByPostIdWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("idPost") Long idPost
    ) {
        Page<Comentario> postPage = comentarioService.findAllComentariosByPostIdPaginated(pagina, tamanho, idPost);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, ComentarioDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<ComentarioDO> findComentariosByUsuarioIdWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("idUsuario") Long idUsuario
    ) {
        Page<Comentario> postPage = comentarioService.findAllComentariosByUsuarioIdPaginated(pagina, tamanho, idUsuario);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, ComentarioDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/com/{texto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<ComentarioDO> findComentariosByTextWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("texto") String texto
    ) {
        Page<Comentario> postPage = comentarioService.findAllComentariosByTextPaginated(pagina, tamanho, texto);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, ComentarioDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<ComentarioDO> findPostsWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho
    ) {
        Page<Comentario> postPage = comentarioService.findAllComentariosPaginated(pagina, tamanho);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, ComentarioDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ComentarioDO findById(@PathVariable(value = "id") Long id) {
        return DozerMapper.parseObject(comentarioService.findById(id), ComentarioDO.class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse create(@RequestBody ComentarioRequest comentarioRequest) {
        Post existingPost = postService.findById(comentarioRequest.getIdPost());
        Usuario usuario = usuarioService.authenticate(comentarioRequest.getEmail(), comentarioRequest.getSenha());

        Comentario comentario = DozerMapper.parseObject(comentarioRequest, Comentario.class);
        comentario.setUsuario(usuario);
        comentario.setPost(existingPost);

        Comentario createdComentario = comentarioService.create(comentario);

        return new ResultadoResponse("O comentario (ID: " + createdComentario.getId() + ") foi criado.", false);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse update(@RequestBody ComentarioRequest comentarioRequest) {
        Usuario usuario = usuarioService.authenticate(comentarioRequest.getEmail(), comentarioRequest.getSenha());
        Comentario existingComentario = comentarioService.findById(comentarioRequest.getId());

        if (!existingComentario.getUsuario().getId().equals(usuario.getId())) {
            throw new PermissionDeniedException("O comentário não pertence ao usuário.");
        }
        
        Post post = postService.findById(existingComentario.getPost().getId());
        
        Comentario comentario = DozerMapper.parseObject(comentarioRequest, Comentario.class);
        comentario.setUsuario(usuario);
        comentario.setPost(post);

        Comentario updatedComentario = comentarioService.update(comentario);

        return new ResultadoResponse("O comentario (ID: " + updatedComentario.getId() + ") foi editado.", false);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse delete(@RequestBody DeleteRequest deleteRequest) {
        Comentario comentario = comentarioService.findById(deleteRequest.getId());
        Usuario usuario = usuarioService.authenticate(deleteRequest.getEmail(), deleteRequest.getSenha());

        if (!comentario.getUsuario().getId().equals(usuario.getId())) {
            throw new PermissionDeniedException("O comentário (ID: " + deleteRequest.getId() + ") não pertence ao usuário.");
        }

        comentarioService.delete(deleteRequest.getId());

        return new ResultadoResponse("O comentario (ID: " + deleteRequest.getId() + ") foi deletado.", false);
    }
}
