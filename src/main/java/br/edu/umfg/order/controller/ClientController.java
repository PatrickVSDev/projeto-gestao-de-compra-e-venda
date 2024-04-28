package br.edu.umfg.order.controller;

import br.edu.umfg.order.domain.Client;
import br.edu.umfg.order.service.ClientService;

public class ClientController {

    public static final String LIST_FORMAT = "|%1$-10s|%2$-15s|%3$-15s|%4$-15s|%5$-15s|\n";

    private final Terminal terminal;
    private final ClientService clientService;

    public ClientController(Terminal terminal, ClientService clientService) {
        this.terminal = terminal;
        this.clientService = clientService;
    }

    public void showClientMenu() {
        boolean invalid;
        boolean started = true;
        do {
            invalid = false;
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|           Cadastro de Clientes            |");
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|  Atalho  |           Submenu              |");
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|   [I]    |     Inserir Novo Cliente       |");
            terminal.printText("|   [A]    |     Alterar Cliente            |");
            terminal.printText("|   [D]    |     Deletar Cliente            |");
            terminal.printText("|   [B]    |     Buscar um Cliente por ID   |");
            terminal.printText("|   [L]    |     Listar Todos Clientes      |");
            terminal.printText("|   [S]    |     Voltar p/ Menu Principal   |");
            terminal.printText("+-------------------------------------------+");
            var option = terminal.readText("Escolha uma opção");
            switch (option.toUpperCase()) {
                case "I":
                    showCreateClientForm();
                    break;
                case "A":
                    break;
                case "D":
                    break;
                case "B":
                    findByID();
                    break;
                case "L":
                    showListClients();
                    break;
                case "S":
                    started = false;
                    break;
                default:
                    invalid = true;
                    terminal.printError("Opção Inválida. Tente novamente!");
            }
        } while (invalid || started);
    }

    private void findByID() {
        do {
            var id = terminal.readText("Informe o ID");
            var client = clientService.getById(Integer.parseInt(id));
            if (client != null) {
                terminal.printSeparatorLine(75);
                terminal.printRow(LIST_FORMAT, "ID", "Nome", "Sobrenome", "CPF", "Nascimento", "Batata");
                terminal.printSeparatorLine(75);
                terminal.printRow(LIST_FORMAT, client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getDocument(),
                        terminal.format(client.getBirth()));
                terminal.printSeparatorLine(75);
            } else {
                terminal.printError("Cliente não encontrado!");
            }
        } while (terminal.wishToContinue());
    }

    private void showListClients() {
        do {
            terminal.printSeparatorLine(75);
            terminal.printRow(LIST_FORMAT, "ID", "Nome", "Sobrenome", "CPF", "Nascimento", "Batata");
            terminal.printSeparatorLine(75);

            Client[] clients = clientService.getAll();
            for (Client client : clients) {
                terminal.printRow(LIST_FORMAT, client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getDocument(),
                        terminal.format(client.getBirth()));
            }
            terminal.printSeparatorLine(75);
        } while (terminal.wishToContinue());
    }

    private void showCreateClientForm() {
        do {
            terminal.printText("Incluindo um novo cliente!");

            Client client = new Client();
            client.setFirstName(terminal.readText("Nome"));
            client.setLastName(terminal.readText("Sobrenome"));
            client.setDocument(terminal.readText("CPF (sem pontuação)"));
            client.setBirth(terminal.readLocalDate("Data de Nascimento"));

            try {
                clientService.create(client);
                terminal.printText("Novo cliente inserido com sucesso!");
            } catch (Exception ex) {
                terminal.printError(ex.getMessage());
            }

        } while (terminal.wishToContinue());
    }
}
