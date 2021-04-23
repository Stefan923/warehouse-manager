package me.stefan923.ordermanager.bll;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import me.stefan923.ordermanager.connection.ConnectionFactory;
import me.stefan923.ordermanager.model.Client;
import me.stefan923.ordermanager.model.Order;
import me.stefan923.ordermanager.model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptFactory {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu_MM_dd__HH_mm_ss_SSS");
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final ReceiptFactory singleInstance = new ReceiptFactory();

    public static ReceiptFactory getInstance() {
        return singleInstance;
    }

    public void createReceipt(Order order, Client client, Product product) {
        try {
            Document document = new Document();
            String time = LocalDateTime.now().format(dateFormatter);
            PdfWriter.getInstance(document, new FileOutputStream("Receipt_" + time + ".pdf"));

            document.open();
            document.add(convertReceiptDataToPhrase(order, client, product));
            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "ReceiptFactory:createReceipt " + e.getMessage());
        }
    }

    private Phrase convertReceiptDataToPhrase(Order order, Client client, Product product) {
        return new Phrase(
                "Client Name: " + client.getName() + "\n" +
                "Client Email: " + client.getEmail() + "\n" +
                "Client Address: " + client.getAddress() + "\n" +
                "Product Name: " + product.getName() + "\n" +
                "Quantity: " + order.getQuantity() + "\n" +
                "Price: " + (product.getPrice() * order.getQuantity()) + "\n"
        );
    }

}
