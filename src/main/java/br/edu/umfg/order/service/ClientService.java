package br.edu.umfg.order.service;

import br.edu.umfg.order.domain.Client;
import br.edu.umfg.order.repository.ClientRepository;
import br.edu.umfg.order.validator.ClientValidator;

public class ClientService {

    private final ClientValidator clientValidator;
    private final ClientRepository clientRepository;

    public ClientService(ClientValidator clientValidator, ClientRepository clientRepository) {
        this.clientValidator = clientValidator;
        this.clientRepository = clientRepository;
    }

    public void create(Client client) {
        clientValidator.validate(client);
        clientRepository.save(client);
    }

    public Client[] getAll() {
        return clientRepository.getAll();
    }

    public Client getById(int id) {
        return clientRepository.getById(id);
    }
}
