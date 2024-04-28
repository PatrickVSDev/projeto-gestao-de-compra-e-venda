package br.edu.umfg.order.controller;

public class MainController {

    private final Terminal terminal;
    private final ClientController clientController;

    public MainController(Terminal terminal, ClientController clientController) {
        this.terminal = terminal;
        this.clientController = clientController;
    }

    public void showMainMenu() {
        var started = true;
        boolean invalid;
        do {
            invalid = false;
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|              Menu Principal               |");
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|  Atalho  |           Submenu              |");
            terminal.printText("+-------------------------------------------+");
            terminal.printText("|   [C]    |     Cadastro de Clientes       |");
            terminal.printText("|   [S]    |     Sair                       |");
            terminal.printText("+-------------------------------------------+");

            var option = terminal.readText("Escolha uma opção");
            switch (option.toUpperCase()) {
                case "C":
                    clientController.showClientMenu();
                    break;
                case "S":
                    started = false;
                    terminal.printText("Até logo e obrigado pelos peixes!");
                    break;
                default:
                    invalid = true;
                    terminal.printError("Opção inválida. Tente novamente");
            }
        } while (invalid || started);

    }

}
