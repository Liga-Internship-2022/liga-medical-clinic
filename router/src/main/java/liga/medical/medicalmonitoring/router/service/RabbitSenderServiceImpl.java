package liga.medical.medicalmonitoring.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.router.api.RabbitSenderService;
import liga.medical.medicalmonitoring.router.model.QueueNames;
import liga.medical.medicalmonitoring.router.model.RabbitMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
