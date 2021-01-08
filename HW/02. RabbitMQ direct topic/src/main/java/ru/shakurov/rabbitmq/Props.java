package ru.shakurov.rabbitmq;

import ru.shakurov.rabbitmq.domain.Request;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class Props {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream stream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("rabbitmq.properties");
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Request> requests = Arrays.asList(
            new Request("Заявление на отпуск", "Введите ФИО", "documents.job.vacation", "job_key"),
            new Request("Заявление на оформление полиса ДМС", "Введите серию и номер паспорта", "documents.job.policy", "job_key"),
            new Request("Заявление на академ. отпуск", "Введите номер студенческого билета", "documents.university.academic", "university_key"),
            new Request("Заявление для перевода на бюджетную форму обучения", "Введите номер студенческого билета", "documents.university.budgetary_form", "university_key")
    );

    public static String getHost() {
        return properties.getProperty("host");
    }

    //типы эксченжей
    public static String getExchangeFanout() {
        return properties.getProperty("exchange.fanout");
    }

    public static String getExchangeDirect() {
        return properties.getProperty("exchange.direct");
    }

    public static String getExchangeTopic() {
        return properties.getProperty("exchange.topic");
    }

    //название эксченжей
    public static String getExchangeFanoutName() {
        return properties.getProperty("exchange.fanout.name");
    }

    public static String getExchangeDirectName() {
        return properties.getProperty("exchange.direct.name");
    }

    public static String getExchangeTopicName() {
        return properties.getProperty("exchange.topic.name");
    }

    //название очередей в direct exchange
    public static String getDirectQueueJobName() {
        return properties.getProperty("exchange.direct.queue.job.name");
    }

    public static String getDirectQueueUniversityName() {
        return properties.getProperty("exchange.direct.queue.university.name");
    }
}
