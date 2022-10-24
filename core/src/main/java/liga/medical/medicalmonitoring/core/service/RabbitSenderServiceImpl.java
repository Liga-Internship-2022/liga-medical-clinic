package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.RabbitMessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException {
        String messageFromJson = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageFromJson);
        log.info("Сообщение [{}] отправлено в очередь [{}]", messageFromJson, queue);
    }

    @Override
    public void sendError(String message) {
        amqpTemplate.convertAndSend(QueueNames.ERROR_QUEUE_NAME, message);
        log.info("Сообщение об ошибке [{}] отправлено в очередь [{}]", message, QueueNames.ERROR_QUEUE_NAME);
    }
}
