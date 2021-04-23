package me.stefan923.ordermanager.businesslayer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import me.stefan923.ordermanager.connection.ConnectionFactory;
import me.stefan923.ordermanager.model.Client;
import me.stefan923.ordermanager.model.Order;
import me.stefan923.ordermanager.model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    private static final ReceiptFactory singleInstance = new ReceiptFactory();

    public static ReceiptFactory getInstance() {
        return singleInstance;
    }

    public void createReceipt(Order order, Client client, Product product) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Receipt_" + order.getId() + ".pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk(convertReceiptDataToString(order, client, product), font);

            document.add(chunk);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "ReceiptFactory:createReceipt " + e.getMessage());
        }
    }

    private String convertReceiptDataToString(Order order, Client client, Product product) {
        return "Order ID: " + order.getId() + "\n" +
                "Client Name: " + client.getName() + "\n" +
                "Client Email: " + client.getEmail() + "\n" +
                "Client Address: " + client.getAddress() + "\n" +
                "Product Name: " + product.getName() + "\n" +
                "Quantity: " + product.getName();
    }

}
