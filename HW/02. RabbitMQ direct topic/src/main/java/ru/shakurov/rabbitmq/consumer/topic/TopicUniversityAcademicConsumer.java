package ru.shakurov.rabbitmq.consumer.topic;

import ru.shakurov.rabbitmq.Props;
import ru.shakurov.rabbitmq.consumer.AbstractConsumer;

import java.util.UUID;

public class TopicUniversityAcademicConsumer extends AbstractConsumer {
    @Override
    protected String getFilename() {
        return "academic_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Props.getExchangeTopicName();
    }

    @Override
    protected String exchangeType() {
        return Props.getExchangeTopic();
    }

    @Override
    protected String exchangeRoutingKey() {
        return "documents.university.academic";
    }

    @Override
    protected String queueName() {
        return exchangeName() + "_university_academic_queue";
    }

    @Override
    protected String templateName() {
        return "academic.html";
    }
}
