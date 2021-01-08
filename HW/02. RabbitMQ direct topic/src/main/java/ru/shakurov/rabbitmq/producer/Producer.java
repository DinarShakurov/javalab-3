package ru.shakurov.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.shakurov.rabbitmq.Props;
import ru.shakurov.rabbitmq.domain.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Props.getHost());

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(Props.getExchangeDirectName(), Props.getExchangeDirect());
            channel.exchangeDeclare(Props.getExchangeTopicName(), Props.getExchangeTopic());

            System.out.println("Выберите нужное: ");
            List<Request> requests = Props.requests;

            for (int i = 0; i < requests.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, requests.get(i).getName());
            }

            Scanner sc = new Scanner(System.in);
            int num = Integer.parseInt(sc.nextLine());

            Request currentRequest = requests.get(num - 1);

            System.out.println(currentRequest.getRequired());
            String line = sc.nextLine();
            byte[] dataBytes = line.getBytes(StandardCharsets.UTF_8);

            channel.basicPublish(Props.getExchangeDirectName(), currentRequest.getDirectKey(), null, dataBytes);
            channel.basicPublish(Props.getExchangeTopicName(), currentRequest.getTopicKey(), null, dataBytes);
        } catch (TimeoutException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
