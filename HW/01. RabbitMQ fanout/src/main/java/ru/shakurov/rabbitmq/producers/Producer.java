package ru.shakurov.rabbitmq.producers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.shakurov.rabbitmq.services.ReaderService;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String EXCHANGE_NAME = "pdf";
    private static final String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ReaderService readerService = new ReaderService();

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            //создаём exchange
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            do {
                byte[] data = readerService.readClient();
                channel.basicPublish(EXCHANGE_NAME, "", null, data);
            } while (scanner.nextInt() != 0);

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
