package liga.medical.medicalmonitoring.core.config;

import model.QueueNames;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private static String HOST;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(HOST);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue getRouterQueue() {
        return new Queue(QueueNames.ROUTER_QUEUE_NAME);
    }

    @Bean("dailyQueue")
    public Queue getDailyQueue() {
        return new Queue(QueueNames.DAILY_QUEUE_NAME);
    }

    @Bean("alertQueue")
    public Queue getAlertQueue() {
        return new Queue(QueueNames.ALERT_QUEUE_NAME);
    }

    @Bean("errorQueue")
    public Queue getErrorQueue() {
        return new Queue(QueueNames.ERROR_QUEUE_NAME);
    }
}