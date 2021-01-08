package ru.shakurov.rabbitmq.consumer.direct;

import ru.shakurov.rabbitmq.Props;
import ru.shakurov.rabbitmq.consumer.AbstractConsumer;

public class DirectJobConsumer extends AbstractConsumer {
    private int count = 0;

    @Override
    protected String getFilename() {
        return "jobCounter.pdf";
    }

    @Override
    protected String exchangeName() {
        return Props.getExchangeDirectName();
    }

    @Override
    protected String exchangeType() {
        return Props.getExchangeDirect();
    }

    @Override
    protected String exchangeRoutingKey() {
        return "job_key";
    }

    @Override
    protected String queueName() {
        return Props.getDirectQueueJobName();
    }

    @Override
    protected String templateName() {
        return "jobCounter.html";
    }

    @Override
    protected String data(String message) {
        return String.valueOf(++count);
    }
}
