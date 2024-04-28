package br.edu.umfg.order;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.umfg.order.controller.ClientController;
import br.edu.umfg.order.controller.MainController;
import br.edu.umfg.order.controller.Terminal;
import br.edu.umfg.order.repository.ClientRepository;
import br.edu.umfg.order.service.ClientService;
import br.edu.umfg.order.validator.ClientValidator;

public class Application {

    public static void main(String[] args) {
        var terminal = new Terminal();
        var cpfValidator = new CPFValidator();
        var clientRepository = new ClientRepository();
        var clientValidator = new ClientValidator(cpfValidator);
        var clientService = new ClientService(clientValidator, clientRepository);
        var clientController = new ClientController(terminal, clientService);
        var mainController = new MainController(terminal, clientController);
        mainController.showMainMenu();
    }
}
