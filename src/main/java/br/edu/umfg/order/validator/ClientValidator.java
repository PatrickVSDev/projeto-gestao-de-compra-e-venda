package br.edu.umfg.order.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.umfg.order.domain.Client;

import static br.edu.umfg.order.validator.Preconditions.*;

public class ClientValidator {

    private final CPFValidator cpfValidator;

    public ClientValidator(CPFValidator cpfValidator) {
        this.cpfValidator = cpfValidator;
    }

    public void validate(Client client){
        checkNotBlank(client.getFirstName(), "Nome do cliente deve ser informado!");
        checkNotBlank(client.getLastName(), "Sobrenome do cliente deve ser informado!");
        checkNotBlank(client.getDocument(), "CPF do cliente deve ser informado!");
        checkNotNull(client.getBirth(), "Nascimento do cliente deve ser informado!");
        checkSize(client.getFirstName(), 3, 15, "Nome do cliente deve ter de 3 a 15 letras!");
        checkSize(client.getLastName(), 5, 20, "Sobrenome do cliente deve ter de 5 a 20 letras!");
        checkSize(client.getDocument(), 11, "CPF do cliente deve ter 11 dígitos!");
        checkAge(client.getBirth(), 18, "Cliente deve ser maior de idade!");
        try {
            cpfValidator.assertValid(client.getDocument());
        } catch (Exception ex){
            throw new IllegalArgumentException("CPF do cliente inválido.", ex);
        }
    }

}
