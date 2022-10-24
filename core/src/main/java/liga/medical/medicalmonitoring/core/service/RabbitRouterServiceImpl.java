package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.RabbitRouterService;
import liga.medical.medicalmonitoring.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.MessageType;
import model.RabbitMessageDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitRouterServiceImpl implements RabbitRouterService {

    private final RabbitSenderService rabbitSenderService;

    private final ObjectMapper objectMapper;

    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
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
                    log.info("Cannot send message [{}] to any queue", rabbitMessageDto);
            }
        } catch (Exception e) {
            rabbitSenderService.sendError(e.getMessage());
        }
    }
}
