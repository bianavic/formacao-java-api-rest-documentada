package org.edu.formacaojavaapirestdocumentada.handler;

import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // capta todas as execoes de negocio (da classe BusinessException)
    // para tratar, converter e retornar msgs + declarativas ao usuario da aplicacao
    @Resource
    private MessageSource messageSource; // capta msgs de origem das excecoes executadas

    private HttpHeaders headers(){ // relacionado ao cabecalho da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // implementacao para constituir a ResponseError
    private ResponseError responseError(String message, HttpStatus statusCode){
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error"); // customizavel
        responseError.setError(message);
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }

    // ao captar as excecoes, ela verifica se a excecao é do TIPO BusinessException
    // caso seja, ela chama o responsehandler handleBusinessException, montando o ResponseError de acordo com a estrutura dela
    // ou seja, a mensagem sera de acordo com a msg montada no controller
    // senao o spring trata como execao generica
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {
            String message = messageSource.getMessage("error.server", new Object[]{e.getMessage()}, null);
            ResponseError error = responseError(message,HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }
    @ExceptionHandler({BusinessException.class})
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(),HttpStatus.CONFLICT);
        return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
    }

}
