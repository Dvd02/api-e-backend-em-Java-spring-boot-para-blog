package br.com.blog.utils;

import org.springframework.data.domain.Pageable;

import br.com.blog.mapper.DozerMapper;
import br.com.blog.response.PaginacaoResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PaginacaoUtil {
    public static Pageable criarPageable(int pagina, int tamanho) {
        if (pagina < 1 || tamanho < 1) {
            throw new IllegalArgumentException("Os parâmetros 'pagina' e 'tamanho' devem ser >= 1.");
        }

        return PageRequest.of(pagina - 1, tamanho);
    }

    public static <T, U> PaginacaoResponse<U> pageToPaginacaoResponse( Page<T> page, Class<U> destinationClass, int pageNumber, int pageSize) {
        List<U> content = DozerMapper.parseListObjects(page.getContent(), destinationClass);
        
        PaginacaoResponse<U> response = new PaginacaoResponse<>();
        response.setIsError(false);
        response.setConteudo(content);
        response.setTemProximaPagina(page.hasNext());
        response.setNumeroDaPagina(pageNumber);
        response.setTamanhoDaPagina(pageSize);
        
        return response;
    }
}
