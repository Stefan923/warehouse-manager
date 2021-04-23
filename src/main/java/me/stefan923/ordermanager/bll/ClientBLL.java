package me.stefan923.ordermanager.bll;

import me.stefan923.ordermanager.bll.validator.ClientAgeValidator;
import me.stefan923.ordermanager.bll.validator.ClientEmailValidator;
import me.stefan923.ordermanager.dao.ClientDAO;
import me.stefan923.ordermanager.model.Client;

import java.util.List;

public class ClientBLL extends AbstractBLL<Client> {

    private final ClientDAO clientDAO;

    public ClientBLL() {
        super(List.of(new ClientEmailValidator(), new ClientAgeValidator()), new ClientDAO());
        clientDAO = (ClientDAO) dao;
    }

    /**
     * Returns the data associated with a class type (Client) and a given name.
     *
     * @param name - the identifier of the element whose data is to be retrieved.
     * @return a list of objects of type Client.
     */
    public List<Client> findByName(String name) {
        return clientDAO.findByName(name);
    }

}
