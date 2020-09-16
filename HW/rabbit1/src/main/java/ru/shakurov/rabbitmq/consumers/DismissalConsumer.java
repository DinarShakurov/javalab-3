package ru.shakurov.rabbitmq.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.shakurov.rabbitmq.domain.RequestType;
import ru.shakurov.rabbitmq.services.PdfService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DismissalConsumer {
    private static final String EXCHANGE_NAME = "pdf";
    private static final String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        PdfService pdfService = new PdfService();
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(1);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            // создаем временную очередь со случайным названием
            String queue = channel.queueDeclare().getQueue();
            // привязали очередь к EXCHANGE_NAME
            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    pdfService.createPdf(message.getBody(), RequestType.DISMISSAL);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }

    }
}
