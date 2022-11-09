package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.RabbitSenderService;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.RabbitMessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import static model.QueueNames.ERROR_QUEUE_NAME;
import static model.SystemType.MESSAGE_ANALYZER;

@Service
@RequiredArgsConstructor
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final LoggingService loggingService;

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException {
        String messageFromJson = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageFromJson);

        loggingService.logQueueMessageSending(messageDto, queue, MESSAGE_ANALYZER);
    }

    @Override
    public void sendError(String message) {
        amqpTemplate.convertAndSend(ERROR_QUEUE_NAME, message);

        loggingService.logQueueSendingException(message, ERROR_QUEUE_NAME, MESSAGE_ANALYZER);
    }
}
