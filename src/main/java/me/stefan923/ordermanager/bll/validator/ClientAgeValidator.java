package me.stefan923.ordermanager.bll.validator;

import me.stefan923.ordermanager.model.Client;

public class ClientAgeValidator implements Validator<Client> {

    private static final int MINIMUM_AGE = 18;

    @Override
    public boolean validate(Client client) {
        if (client != null && client.getAge() >= MINIMUM_AGE) {
            return true;
        }

        throw new IllegalArgumentException("This is not a valid age!");
    }

}
