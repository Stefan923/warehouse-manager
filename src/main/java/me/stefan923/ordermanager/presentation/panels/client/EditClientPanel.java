package me.stefan923.ordermanager.presentation.panels.client;

import me.stefan923.ordermanager.model.Client;

public class EditClientPanel extends ClientPanel {

    private Client client;

    public void setClient(Client client) {
        this.client = client;
        updateFields();
    }

    public Client getClient() {
        return client;
    }

    private void updateFields() {
        nameTextField.setText(client.getName());
        addressTextField.setText(client.getAddress());
        emailTextField.setText(client.getEmail());
        ageTextField.setText(String.valueOf(client.getAge()));
    }

}
