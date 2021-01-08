package ru.shakurov.rabbitmq.consumer.topic;

import ru.shakurov.rabbitmq.Props;
import ru.shakurov.rabbitmq.consumer.AbstractConsumer;

import java.util.UUID;

public class TopicUniversityBudgetaryFromConsumer extends AbstractConsumer {
    @Override
    protected String getFilename() {
        return "budgetary_form_" + UUID.randomUUID() + ".pdf";
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
        return "documents.university.budgetary_form";
    }

    @Override
    protected String queueName() {
        return exchangeName() + "_university_budgetary_form_queue";
    }

    @Override
    protected String templateName() {
        return "budgetary_form.html";
    }
}
