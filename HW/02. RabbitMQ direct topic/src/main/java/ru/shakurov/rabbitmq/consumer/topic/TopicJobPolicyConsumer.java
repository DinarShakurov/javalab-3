package ru.shakurov.rabbitmq.consumer.topic;

import ru.shakurov.rabbitmq.Props;
import ru.shakurov.rabbitmq.consumer.AbstractConsumer;

import java.util.UUID;

public class TopicJobPolicyConsumer extends AbstractConsumer {
    @Override
    protected String getFilename() {
        return "policy_" + UUID.randomUUID() + ".pdf";
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
        return "documents.job.policy";
    }

    @Override
    protected String queueName() {
        return exchangeName() + "_job_policy_queue";
    }

    @Override
    protected String templateName() {
        return "policy.html";
    }
}
