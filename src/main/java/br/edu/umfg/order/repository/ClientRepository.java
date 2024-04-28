package br.edu.umfg.order.repository;

import br.edu.umfg.order.domain.Client;

import java.time.LocalDate;
import java.util.Arrays;

public class ClientRepository {

    private Client[] database = new Client[1];
    private int sequence = 1;

    public ClientRepository() {
        var client = new Client();
        client.setId(1);
        client.setFirstName("Rafael");
        client.setLastName("Rasso");
        client.setDocument("18998767074");
        client.setBirth(LocalDate.now().minusYears(20));
        database[0] = client;

    }

    public void save(Client client) {
        database = Arrays.copyOf(database, database.length + 1);
        client.setId(++sequence);
        database[database.length - 1] = client;
    }

    public Client[] getAll() {
        return database;
    }

    public Client getById(int id) {
        for (Client client : database) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }
}
