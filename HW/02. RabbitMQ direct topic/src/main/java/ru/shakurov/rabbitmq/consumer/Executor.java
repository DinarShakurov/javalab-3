package ru.shakurov.rabbitmq.consumer;

import ru.shakurov.rabbitmq.consumer.direct.DirectJobConsumer;
import ru.shakurov.rabbitmq.consumer.direct.TopicUniversityConsumer;
import ru.shakurov.rabbitmq.consumer.topic.TopicJobPolicyConsumer;
import ru.shakurov.rabbitmq.consumer.topic.TopicJobVacationConsumer;
import ru.shakurov.rabbitmq.consumer.topic.TopicUniversityAcademicConsumer;
import ru.shakurov.rabbitmq.consumer.topic.TopicUniversityBudgetaryFromConsumer;

public class Executor {

    public static void main(String[] args) {
        new DirectJobConsumer().run();
        new TopicUniversityConsumer().run();
        new TopicJobPolicyConsumer().run();
        new TopicJobVacationConsumer().run();
        new TopicUniversityAcademicConsumer().run();
        new TopicUniversityBudgetaryFromConsumer().run();
    }
}
