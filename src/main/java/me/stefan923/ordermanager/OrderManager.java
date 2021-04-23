package me.stefan923.ordermanager;

import me.stefan923.ordermanager.businesslayer.ClientBLL;
import me.stefan923.ordermanager.businesslayer.OrderBLL;
import me.stefan923.ordermanager.businesslayer.ProductBLL;
import me.stefan923.ordermanager.presentation.Controller;
import me.stefan923.ordermanager.presentation.View;

public class OrderManager {

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view, new ClientBLL(), new ProductBLL(), new OrderBLL());
        controller.load();
        view.load();
        view.setVisible(true);
    }

}
