package org.edu.formacaojavaapirestdocumentada.handler;

public class BusinessException extends RuntimeException {

    // base para todas as execoes de negocio
    // cria uma execao central q spring intercepta qdo disparado esse tipo de excecao,
    // fazendo devido redirecionamento diante do globalhandler

    public BusinessException(String mensagem) {
        super(mensagem);
    }

    // estrutura que cria conteudo dinamico
    public BusinessException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }

}
