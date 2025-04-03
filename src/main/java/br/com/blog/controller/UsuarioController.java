
package br.com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.blog.mapper.DozerMapper;
import br.com.blog.mapper.curtom.UsuarioDO;
import br.com.blog.model.Usuario;
import br.com.blog.requests.LoginRequest;
import br.com.blog.requests.UsuarioUpdateRequest;
import br.com.blog.response.LoginResponse;
import br.com.blog.response.PaginacaoResponse;
import br.com.blog.response.ResultadoResponse;
import br.com.blog.services.UsuarioService;
import br.com.blog.utils.PaginacaoUtil;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDO findById(@PathVariable(value = "id") Long id) {
        return DozerMapper.parseObject(usuarioService.findById(id), UsuarioDO.class);
    }

    @GetMapping(value = "/pagina/{pagina}/tamanho/{tamanho}/chamado/{nomeUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginacaoResponse<UsuarioDO> findUsuariosByNomeWithPagination(
        @PathVariable("pagina") int pagina,
        @PathVariable("tamanho") int tamanho,
        @PathVariable("nomeUsuario") String nomeUsuario
    ) {
        Page<Usuario> postPage = usuarioService.findAllUsuarioByNomePaginated(pagina, tamanho, nomeUsuario);
        return PaginacaoUtil.pageToPaginacaoResponse(postPage, UsuarioDO.class, pagina, tamanho);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse create(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.create(usuario);

        return new ResultadoResponse("O usuario (ID: " + createdUsuario.getId() + ") foi criado", false);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse update(@RequestBody UsuarioUpdateRequest request) {
        Usuario existingUser = usuarioService.authenticate(request.getEmailAntigo(), request.getSenhaAntiga());

        existingUser.setEmail(request.getEmailNovo());
        existingUser.setSenha(request.getSenhaNova());
        existingUser.setNome(request.getNomeNovo());
        existingUser.setCorFoto(request.getCorFoto());

        usuarioService.update(existingUser);

        return new ResultadoResponse("O usuario (ID: " + existingUser.getId() + ") foi editado", false);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoResponse delete(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.authenticate(loginRequest.getEmail(), loginRequest.getSenha());        
        
        usuarioService.delete(usuario.getId());
        
        return new ResultadoResponse("O usuario (ID: " + usuario.getId() + ") foi deletado", false);
    }
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.authenticate(loginRequest.getEmail(), loginRequest.getSenha());
        
        return new LoginResponse(usuario.getId());
    }
}
