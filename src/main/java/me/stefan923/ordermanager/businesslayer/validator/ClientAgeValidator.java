package me.stefan923.ordermanager.businesslayer.validator;

import me.stefan923.ordermanager.model.Client;

public class ClientAgeValidator implements Validator<Client> {

    private static final int MINIMUM_AGE = 18;

    @Override
    public boolean validate(Client client) {
        return client != null && client.getAge() >= MINIMUM_AGE;
    }

}
