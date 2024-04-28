package br.edu.umfg.order.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Terminal {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void printText(String text) {
        System.out.format("> %s\n", text);
    }

    public void printError(String text) {
        System.out.format("ERROR> %s\n", text);
    }

    public String readText(String label) {
        System.out.format(">> %s: ", label);
        return SCANNER.nextLine();
    }

    public LocalDate readLocalDate(String label) {
        while (true) {
            try {
                System.out.format(">> %s (dd/MM/yyyy): ", label);
                return LocalDate.parse(SCANNER.nextLine(), DATE_FORMAT);
            } catch (DateTimeParseException ex) {
                printError("Favor informar um valor válido! Ex: 10/12/1995 ");
            }
        }
    }

    public boolean wishToContinue() {
        System.out.println("Deseja continuar? [S]im / [N]ão");
        var wishContinue = false;
        var invalid = true;
        while (invalid) {
            var answer = SCANNER.nextLine();
            if (!answer.equalsIgnoreCase("S") && !answer.equalsIgnoreCase("N")) {
                printError("Opcão inválida. Tente novamente.");
            } else {
                wishContinue = answer.equalsIgnoreCase("S");
                invalid = false;
            }
        }
        return wishContinue;
    }

    public void printRow(String template, Object... args) {
        System.out.format(template, args);
    }

    public void printSeparatorLine(int size) {
        var text = new StringBuilder();
        for (int i = 1; i < size; i++) {
            text.append("-");
        }
        var format = "+%s+\n";
        printRow(format, text.toString());
    }

    public String format(LocalDate data) {
        if (data != null) {
            return data.format(DATE_FORMAT);
        }
        return null;
    }


}
