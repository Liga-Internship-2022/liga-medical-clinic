package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.RabbitRouterService;
import liga.medical.medicalmonitoring.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.MessageType;
import model.RabbitMessageDto;
import org.springframework.stereotype.Service;

import static model.SystemType.MEDICAL_MONITORING;

@Service
@RequiredArgsConstructor
public class RabbitRouterServiceImpl implements RabbitRouterService {

    private final LoggingService loggingService;
    private final RabbitSenderService rabbitSenderService;

    private final ObjectMapper objectMapper;

    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            loggingService.logQueueMessageReceiving(rabbitMessageDto, QueueNames.ROUTER_QUEUE_NAME, MEDICAL_MONITORING);
            MessageType messageType = rabbitMessageDto.getMessageType();

            switch (messageType) {
                case DAILY:
                    rabbitSenderService.sendMessage(rabbitMessageDto, QueueNames.DAILY_QUEUE_NAME);
                    break;
                case ALERT:
                    rabbitSenderService.sendMessage(rabbitMessageDto, QueueNames.ALERT_QUEUE_NAME);
                    break;
                case ERROR:
                    rabbitSenderService.sendMessage(rabbitMessageDto, QueueNames.ERROR_QUEUE_NAME);
                    break;
                default:
                    rabbitSenderService.sendError("Не удалось отправить сообщение [" +
                            rabbitMessageDto + "] в одну из очередей");
            }
        } catch (Exception e) {
            rabbitSenderService.sendError(e.getMessage());
        }
    }
}
