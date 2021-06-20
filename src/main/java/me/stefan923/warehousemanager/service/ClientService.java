package me.stefan923.warehousemanager.service;

import me.stefan923.warehousemanager.service.validator.ClientAgeValidator;
import me.stefan923.warehousemanager.service.validator.ClientEmailValidator;
import me.stefan923.warehousemanager.repository.ClientRepository;
import me.stefan923.warehousemanager.model.Client;

import java.util.List;

public class ClientService extends Service<Client> {

    private final ClientRepository clientRepository;

    public ClientService() {
        super(List.of(new ClientEmailValidator(), new ClientAgeValidator()), new ClientRepository());
        clientRepository = (ClientRepository) dao;
    }

    /**
     * Returns the data associated with a class type (Client) and a given name.
     *
     * @param name - the identifier of the element whose data is to be retrieved.
     * @return a list of objects of type Client.
     */
    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

}
