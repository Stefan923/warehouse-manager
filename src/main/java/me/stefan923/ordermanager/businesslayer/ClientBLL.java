package me.stefan923.ordermanager.businesslayer;

import me.stefan923.ordermanager.businesslayer.validator.ClientAgeValidator;
import me.stefan923.ordermanager.businesslayer.validator.ClientEmailValidator;
import me.stefan923.ordermanager.dao.ClientDAO;
import me.stefan923.ordermanager.model.Client;

import java.util.List;

public class ClientBLL extends AbstractBLL<Client> {

    private final ClientDAO clientDAO;

    public ClientBLL() {
        super(List.of(new ClientEmailValidator(), new ClientAgeValidator()), new ClientDAO());
        clientDAO = (ClientDAO) dao;
    }

    public List<Client> findByName(String name) {
        return clientDAO.findByName(name);
    }

}
