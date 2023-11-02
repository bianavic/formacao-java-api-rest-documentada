package org.edu.formacaojavaapirestdocumentada.handler;

public class RequiredFieldException extends BusinessException {
    public RequiredFieldException(String field) {
        super("o campo %s é obrigatorio", field);
    }
}
