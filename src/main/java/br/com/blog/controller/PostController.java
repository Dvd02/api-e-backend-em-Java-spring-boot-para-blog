package br.com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.blog.exceptions.PermissionDeniedException;
import br.com.blog.mapper.DozerMapper;
import br.com.blog.mapper.curtom.PostDO;
import br.com.blog.model.Post;
import br.com.blog.model.Usuario;
import br.com.blog.requests.DeleteRequest;
import br.com.blog.requests.PostRequest;
import br.com.blog.response.PaginacaoResponse;
import br.com.blog.response.ResultadoResponse;
import br.com.blog.services.PostService;
import br.com.blog.services.UsuarioService;
import br.com.blog.utils.PaginacaoUtil;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDO findById(@PathVariable(value = "id") Long id) {
        return DozerMapper.parseObject(postService.findById(id), PostDO.class);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/do_usuario_chamado/{nomeUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<PostDO> findPostsByUsuarioNomeWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("nomeUsuario") String nomeUsuario
    ) {
        Page<Post> postPage = postService.findAllPostsByUsuarioNomePaginated(pagina, tamanho, nomeUsuario);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, PostDO.class, pagina, tamanho);
    }
    
    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/do_usuario_id/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<PostDO> findPostsByUsuarioIdWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("idUsuario") Long idUsuario
    ) {
        Page<Post> postPage = postService.findAllPostsByUsuarioIdPaginated(pagina, tamanho, idUsuario);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, PostDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/com/{texto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<PostDO> findPostsByTextWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("texto") String texto
    ) {
        Page<Post> postPage = postService.findAllPostsByTextPaginated(pagina, tamanho, texto);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, PostDO.class, pagina, tamanho);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<PostDO> findPostsWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho
    ) {
        Page<Post> postPage = postService.findAllPostsPaginated(pagina, tamanho);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, PostDO.class, pagina, tamanho);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse create(@RequestBody PostRequest postRequest) {
        Usuario usuario = usuarioService.authenticate(postRequest.getEmail(), postRequest.getSenha());

        Post post = DozerMapper.parseObject(postRequest, Post.class);
        post.setUsuario(usuario);

        Post createdPost = postService.create(post);
        
        return new ResultadoResponse("O post (ID: " + createdPost.getId() + ") foi criado.", false);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse update(@RequestBody PostRequest postRequest) {
        Post existingPost = postService.findById(postRequest.getId());
        Usuario usuario = usuarioService.authenticate(postRequest.getEmail(), postRequest.getSenha());

        if (!existingPost.getUsuario().getId().equals(usuario.getId())) {
            throw new PermissionDeniedException("O post não pertence ao usuário.");
        }

        postService.update(DozerMapper.parseObject(existingPost, Post.class));

        return new ResultadoResponse("O post (ID: " + existingPost.getId() + ") foi editado.", false);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse delete(@RequestBody DeleteRequest deleteRequest) {
        Post post = postService.findById(deleteRequest.getId());
        Usuario usuario = usuarioService.authenticate(deleteRequest.getEmail(), deleteRequest.getSenha());

        if (!post.getUsuario().getId().equals(usuario.getId())) {
            throw new PermissionDeniedException("O post (ID: " + deleteRequest.getId() + ") não pertence ao usuário.");
        }

        postService.delete(deleteRequest.getId());

        return new ResultadoResponse("O post (ID: " + deleteRequest.getId() + ") foi deletado.", false);
    }
}
